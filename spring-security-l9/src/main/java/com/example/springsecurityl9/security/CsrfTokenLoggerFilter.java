package com.example.springsecurityl9.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CsrfTokenLoggerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        System.out.println("token = " + token.getToken() + " : " + token.getHeaderName() + " : " + token.getParameterName());
        filterChain.doFilter(request, response);

    }
}
