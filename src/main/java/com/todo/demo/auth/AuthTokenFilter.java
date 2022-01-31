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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends AbstractAuthenticationProcessingFilter {


    public AuthTokenFilter(){
        super(new AntPathRequestMatcher("/**"));
    }

    public static AuthTokenFilter fromPatternAndMnger(String pattern, AuthenticationManager authManager){
        return new AuthTokenFilter(pattern, authManager);
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
        var token = header[1];
        return this.getAuthenticationManager().authenticate(new TokenAuthentication(token,false));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);

    }
}
