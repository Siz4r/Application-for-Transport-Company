package com.example.licencjat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/auth/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000");
                registry.addMapping("/api/user/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/api/employees/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "DELETE");
                registry.addMapping("/api/clients/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "DELETE");
                registry.addMapping("/api/orders/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "PATCH", "PUT");
                registry.addMapping("/api/companies/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE");
                registry.addMapping("/api/stuffs/**").allowCredentials(true).allowedOrigins("http://localhost:7692", "http://localhost:3000").allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE");
            }
        };
    }
}
