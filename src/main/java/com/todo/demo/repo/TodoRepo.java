package com.todo.demo.repo;

import com.todo.demo.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepo extends MongoRepository<Todo, String> {

}
