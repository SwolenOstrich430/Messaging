package com.app.Message_Backend.auth;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImp implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final String token;

    public UserDetailsImp(String username, String password, List<SimpleGrantedAuthority> authorities, String token) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.token = token;
    }

    public UserDetailsImp(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
        authorities = new ArrayList<>();
    }

    public UserDetailsImp(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new ArrayList<>();
        token = "";
    }

    public UserDetailsImp() {
        this.username = "";
        this.password = "";
        authorities = new ArrayList<>();
        token = "";
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
