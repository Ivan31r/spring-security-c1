package com.example.springsecurityl31.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class ProjectConfig {

    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService() {
        var userJohn = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();
        var userBill = User.withUsername("bill")
                .password("12345")
                .authorities("write")
                .build();
        var userDetailsService = new MapReactiveUserDetailsService(userJohn, userBill);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) { //endpoint configuration
        return httpSecurity
                .authorizeExchange() //exchange = request в НЕ реактивных приложениях
                .anyExchange().hasAuthority("read")
                .and()
                .httpBasic()
                .and()
                .build();
    }
}
