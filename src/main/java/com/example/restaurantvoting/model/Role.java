package com.example.restaurantvoting.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
