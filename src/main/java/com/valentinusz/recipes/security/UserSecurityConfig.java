package com.valentinusz.recipes.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserSecurityConfig {
    @Bean
    public SecurityFilterChain userSecurityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        httpSecurity.securityMatcher("/users").authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.GET, "/users/{id}").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/users/{id}/restrictions").permitAll();
        });
        return httpSecurity.build();
    }
}
