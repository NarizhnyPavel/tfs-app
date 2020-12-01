package com.TimeForStudy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Настройки jwt токена.
 *
 * @author Velikanov Artyom.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    @Data
    public static class token {

        private String secret;
        private String expire;
    }

}