package com.example.config.props;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ConfigurationProperties(prefix = "jdbc")
public class Settings {
    private String driverClassName;
    private String url;
}
