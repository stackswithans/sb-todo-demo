package com.todo.demo.auth;
import com.todo.demo.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

public class TokenAuthentication implements Authentication {
    private String authToken;
    private boolean isValid;
    private User user = null;

    public TokenAuthentication(String authToken, boolean isValid) {
        this.authToken = authToken;
        this.isValid = isValid;
    }

    public TokenAuthentication(String authToken, boolean isValid, User user) {
        this.authToken = authToken;
        this.isValid = isValid;
        this.user = user;
    }

    @Override
    public List<GrantedAuthority> getAuthorities(){
        return new ArrayList<>();
    }

    @Override
    public String getCredentials() {
        return authToken;
    }

    @Override
    public User getDetails() {
        return user;
    }

    @Override
    public String getPrincipal() {
        return authToken;
    }

    @Override
    public boolean isAuthenticated() {
        return isValid;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
