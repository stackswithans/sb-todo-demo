package com.todo.demo.controllers;


import com.todo.demo.models.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @GetMapping("/")
    public Todo getTodo(){
        return new Todo("Do something", "little india", "26-01-2021");
    }
}
