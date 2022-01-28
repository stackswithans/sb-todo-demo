package com.todo.demo.repo;
import com.todo.demo.models.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthTokenRepo extends MongoRepository<AuthToken, String> {
}
