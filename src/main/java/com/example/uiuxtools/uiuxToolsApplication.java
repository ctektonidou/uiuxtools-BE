package com.example.uiuxtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.example.uiuxtools")
@EnableWebSecurity
public class uiuxToolsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(uiuxToolsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(uiuxToolsApplication.class, args);
    }

    private static final int CORS_MAX_AGE = 3600;
    private static final String[] CORS_ALLOWED_ORIGINS = {"*"};
    private static final String[] CORS_ALLOWED_METHODS = {
            "POST", "GET", "OPTIONS", "PUT", "DELETE"
    };
    private static final String[] CORS_ALLOWED_HEADERS = {
            "X-Requested-With", "content-type", "Authorization"
    };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(CORS_ALLOWED_ORIGINS)
                        .allowedMethods(CORS_ALLOWED_METHODS)
                        .maxAge(CORS_MAX_AGE)
                        .allowedHeaders(CORS_ALLOWED_HEADERS)
                ;
            }
        };
    }
}