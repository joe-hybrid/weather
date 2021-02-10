package com.example.weather.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger configuration.
 */
@ConfigurationProperties(prefix = "swagger")
@Data
public class OpenApiProperties {
    // external host name
    private String host;
}
