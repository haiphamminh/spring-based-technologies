package com.example.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;

/*@Configuration
@EnableWebSocketMessageBroker*/
public class WebSocketSecurityConfigurationBackup extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    public static final String IP_ADDR = "IP_ADDRESS";
    private static final String CLIENT_LIB_URL = "https://cdn.jsdelivr.net/sockjs/1.0.3/sockjs.min.js";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                                      Map<String, Object> attributes) {
                        Principal principal = request.getPrincipal();
                        if (principal == null) {
                            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                            authorities.add(new SimpleGrantedAuthority("anonymous"));
                            principal = new AnonymousAuthenticationToken("anonymous", "anonymous", authorities);
                        }
                        return principal;
                    }
                })
                .setAllowedOrigins("*")
                .withSockJS()
                .setInterceptors(httpSessionHandshakeInterceptor())
                .setClientLibraryUrl(CLIENT_LIB_URL);
    }

    @Bean
    public HandshakeInterceptor httpSessionHandshakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                           WebSocketHandler wsHandler,
                                           Map<String, Object> attributes) throws Exception {
                if (request instanceof ServletServerHttpRequest) {
                    ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
                    attributes.put(IP_ADDR, serverHttpRequest.getRemoteAddress());
                }
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Exception exception) {

            }
        };
    }

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        // @formatter:off
        messages.nullDestMatcher().authenticated()
                .simpDestMatchers("/topic/tracker").hasRole("ADMIN")
                .simpDestMatchers("/topic/**").authenticated()
                .simpDestMatchers("/user/queue/**").authenticated()
                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
                .anyMessage().denyAll();
        // @formatter:on
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
