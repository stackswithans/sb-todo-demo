package com.todo.demo.controllers;
import com.todo.demo.auth.CustomError;
import com.todo.demo.models.AuthToken;
import com.todo.demo.models.User;
import com.todo.demo.payloads.AuthRequest;
import com.todo.demo.payloads.AuthResponse;
import com.todo.demo.repo.AuthTokenRepo;
import com.todo.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    UserRepo users;
    @Autowired
    AuthTokenRepo tokens;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody  AuthRequest creds){
        var maybeUser = users.findById(creds.getUsername());
        if(maybeUser.isEmpty() || !maybeUser.get().getPassword().equals(creds.getPassword())){
            throw new CustomError();
        }
        var user = maybeUser.get();
        var tok = tokens.findByUser_Username(user.getUsername());
        if(tok.isEmpty()){
            var newTok = new AuthToken(user);
            tokens.save(newTok);
            tok = Optional.of(newTok);
        }
        return new AuthResponse(tok.get().getToken());
    }
}
