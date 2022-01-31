package com.todo.demo.auth;
import com.todo.demo.models.User;
import com.todo.demo.repo.AuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenProvider implements AuthenticationProvider {

    @Autowired
    AuthTokenRepo tokens;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        TokenAuthentication tok = (TokenAuthentication) auth;
        var tokObj = tokens.findById(tok.getPrincipal());
        System.out.println(tokObj.isEmpty());
        if(tokObj.isEmpty()){
            throw new BadCredentialsException("Invalid API Key.");
        }
        var token = tokObj.get();
        System.out.println(token.getToken());
        User user = token.getUser();
        return new TokenAuthentication(token.getToken(), true, user);
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(TokenAuthentication.class);
    }
}
