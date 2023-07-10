package com.valentinusz.recipes.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RecipeSecurityConfig {
    @Bean
    public SecurityFilterChain recipeSecurityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        httpSecurity.securityMatcher("/recipes")
                    .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET, "/recipes")
                                                                 .permitAll());
        return httpSecurity.build();
    }
}
