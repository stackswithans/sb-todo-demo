package com.todo.demo.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("authTokens")
public class AuthToken {
    @Id
    private String token;
    private User user;

    public AuthToken(User user){
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
