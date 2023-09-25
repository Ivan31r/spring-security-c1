package com.example.springsecurityl5.security.filter;

import com.example.springsecurityl5.security.Authentication.CustomAuthentication;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter implements Filter {

    private final AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorization = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        var customAuthentication = new CustomAuthentication(authorization, null);
        try {
            Authentication authResult = authenticationManager.authenticate(customAuthentication);
            if (authResult.isAuthenticated()) { // необязательная проверка
                SecurityContextHolder.getContext().setAuthentication(authResult);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (AuthenticationException e) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
