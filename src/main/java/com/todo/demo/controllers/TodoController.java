package com.todo.demo.controllers;


import com.todo.demo.models.Todo;
import com.todo.demo.repo.TodoRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private TodoRepo todoRepo;

    public TodoController(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }

    @GetMapping("/")
    public List<Todo> getTodo(){
        return todoRepo.findAll();
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo newTodo(@RequestBody Todo todo){
        todoRepo.save(todo);
        return todo;
    }
}
