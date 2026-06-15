package com.anoulam.anoulam_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/dishes/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST")
                .allowedHeaders("Content-Type");
    }
} 
