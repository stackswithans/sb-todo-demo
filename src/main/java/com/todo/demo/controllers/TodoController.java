package com.todo.demo.controllers;


import com.todo.demo.models.Todo;
import com.todo.demo.repo.TodoRepo;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TodoController {

    private TodoRepo todoRepo;

    public TodoController(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }

    @GetMapping({"/todos", "/"})
    public List<Todo> getTodo(){
        return todoRepo.findAll();
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo newTodo(@RequestBody Todo todo){
        todoRepo.save(todo);
        return todo;
    }

    @PostMapping("/todos/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public void changeDoneStatus(@PathVariable String todoId){
        final var todo = todoRepo.findById(new ObjectId(todoId)).get();
        todo.setDone(!todo.isDone());
        todoRepo.save(todo);
    }

    @GetMapping("/todos/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public Todo viewTodo(@PathVariable String todoId){
        return todoRepo.findById(new ObjectId(todoId)).get() ;
    }


    @DeleteMapping("/todos/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String todoId){
        todoRepo.deleteById(new ObjectId(todoId));
    }
}
