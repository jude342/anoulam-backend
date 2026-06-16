package com.anoulam.anoulam_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry1) {    
        registry1.addInterceptor(securityInterceptor)
                .addPathPatterns("/api/v1/dishes/**");


        registry1.addInterceptor(rateLimitInterceptor)
                 .addPathPatterns("/api/v1/dishes/**");
    }
} 
