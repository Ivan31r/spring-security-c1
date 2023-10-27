package com.example.springsecurityl25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        var userBill = User.withUsername("bill")
                .password("12345")
                .authorities("read")
                .build();
        var userJohn = User.withUsername("john")
                .password("12345")
                .authorities("write")
                .build();
        inMemoryUserDetailsManager.createUser(userBill);
        inMemoryUserDetailsManager.createUser(userJohn);
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        /*
        matchers:
        -mvc
        -ant
        -> they use ANT path expressions
        */
        http
                .authorizeRequests()
                .mvcMatchers("/a/**").hasAuthority("read")
                .mvcMatchers("/b/*").hasAuthority("write")
                .mvcMatchers("/c/{name}").authenticated()
                .anyRequest()
                .denyAll();
    }
}
