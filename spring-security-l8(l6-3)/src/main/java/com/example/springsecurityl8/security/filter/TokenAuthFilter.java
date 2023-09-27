package com.example.springsecurityl8.security.filter;

import com.example.springsecurityl8.security.authentications.TokenAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("Authorization");
        Authentication tokenAuth = new TokenAuthentication(token, null);
        Authentication authenticate = authenticationManager.authenticate(tokenAuth);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
//            Authentication authentication = tokenManager.authenticate(tokenAuth);   //Если бы TokenManager был реализован корректно (как AuthenticationManager)
//            SecurityContextHolder.getContext().setAuthentication(authentication);   //то наш обьект аутентификации закинули бы в контекст таким образом
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
