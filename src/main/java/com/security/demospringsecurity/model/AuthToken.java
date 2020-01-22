package com.security.demospringsecurity.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthToken {
    private Long id;
    private String token;
    private String role;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    public AuthToken() {

    }




    public AuthToken(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public AuthToken(Long id, String token, String role) {
        this.id = id;
        this.token = token;
        this.role = role;
    }

    public AuthToken(String token, Collection<? extends GrantedAuthority> authorities,Long id) {
        this.token = token;
        this.authorities = authorities;
        this.id = id;
    }

    public AuthToken(String token, String authority, Long id) {
        this.token = token;
        this.role = authority;
        this.id = id;
    }

    public AuthToken(Long id, String name, String token, String authority) {
        this.token = token;
        this.name = name;
        this.role = authority;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
