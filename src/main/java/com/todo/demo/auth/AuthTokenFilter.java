package com.todo.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends AbstractAuthenticationProcessingFilter {

    public AuthTokenFilter(){
        super(new AntPathRequestMatcher("/**"));
    }

    public static AuthTokenFilter fromPattern(String pattern){
        return new AuthTokenFilter(new AntPathRequestMatcher(pattern));
    }

    protected AuthTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    protected AuthTokenFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        var rawHeader = request.getHeader("Authorization");
        if(rawHeader == null){
            throw new AuthenticationCredentialsNotFoundException("Badly formatted authorization header");
        }
        var header = rawHeader.split(" ");
        if( header.length != 2 || !header[0].equals("Token")){
            throw new AuthenticationCredentialsNotFoundException("Badly formatted authorization header");
        }
        var token = header[0];
        return new TokenAuthentication(token,false);
    }
}
