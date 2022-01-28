package com.todo.demo.controllers;
import com.todo.demo.payloads.AuthRequest;
import com.todo.demo.payloads.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {


    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse auth(@RequestBody  AuthRequest creds){
        System.out.println(creds.getPassword());
        return new AuthResponse(creds.getPassword());
    }
}
