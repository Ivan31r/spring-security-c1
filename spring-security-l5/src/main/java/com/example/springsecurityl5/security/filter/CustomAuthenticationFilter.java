package com.example.springsecurityl5.security.filter;

import com.example.springsecurityl5.security.Authentication.CustomAuthentication;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter implements Filter {

    private final AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorization = ((HttpServletRequest) servletRequest).getHeader("Authentication");
        var customAuthentication = new CustomAuthentication(authorization, null);
        Authentication authResult = authenticationManager.authenticate(customAuthentication);
        if (authResult.isAuthenticated()) { // необязательная проверка
            SecurityContextHolder.getContext().setAuthentication(authResult);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
