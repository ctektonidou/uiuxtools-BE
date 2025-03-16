package com.example.uiuxtools.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Loading Security Configuration...");


        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/api/search").permitAll()
                                .requestMatchers("/api/tools").permitAll()
                                .requestMatchers("/api/user").permitAll()
                                .requestMatchers("/api/user/**").permitAll()
                                .requestMatchers("/api/tools/batch").permitAll()
                                .requestMatchers("/api/tools/compare").permitAll()
                                .requestMatchers("/api/feature/group").permitAll()
                                .requestMatchers("/api/feature/item").permitAll()
                                .requestMatchers("/api/tools/searchByFeatures").permitAll()
//                              .requestMatchers("/api/evaluation").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
//                        .requestMatchers("/api/carsForDelearship").permitAll()
//                        .requestMatchers("/api/user/role/dealership").hasAnyAuthority(Role.ADMIN.name())
//                        .requestMatchers("/api/reservation/test-drive").permitAll()
//                        .requestMatchers("/api/car/renew/**").hasAnyAuthority(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("Security Configuration Loaded: /api/searchByFeatures is public");

        return http.build();
    }
}