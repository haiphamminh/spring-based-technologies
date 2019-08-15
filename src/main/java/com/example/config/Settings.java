package com.example.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jdbc")
@Value
public class Settings {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
