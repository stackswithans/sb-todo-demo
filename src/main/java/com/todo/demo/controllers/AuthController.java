package com.todo.demo.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public String auth(){
        return "You can access this!";
    }
}
