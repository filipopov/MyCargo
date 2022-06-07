package com.example.moekargo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SENDER, SUPPLIER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
