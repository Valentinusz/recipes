package com.valentinusz.recipes.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

public class UserService {
    private DelegatingPasswordEncoder passwordEncoder;

    public UserService() {
        SecurityContextHolder.getContext().getAuthentication();
    }

    public UserService(@Autowired DelegatingPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(@NotNull String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(@NotNull String rawPassword, @NotNull String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void setPasswordEncoder(DelegatingPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
