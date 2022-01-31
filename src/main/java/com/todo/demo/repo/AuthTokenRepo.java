package com.todo.demo.repo;
import com.todo.demo.models.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AuthTokenRepo extends MongoRepository<AuthToken, String> {
    Optional<AuthToken> findByUser_Username(String username);
}
