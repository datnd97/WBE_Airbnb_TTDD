package com.security.demospringsecurity.model.login;

import java.util.Collection;

public class AuthToken {
    private String token;
    private String role;

    public AuthToken() {

    }

    public AuthToken(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
