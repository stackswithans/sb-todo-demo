package com.todo.demo.auth;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "Invalid credentials")
public class CustomError extends RuntimeException{
}
