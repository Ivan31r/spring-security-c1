package com.example.springsecurityl5.configuration;

import com.example.springsecurityl5.security.filter.CustomAuthenticationFilter;
import com.example.springsecurityl5.security.provider.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ProjectConfig {

    private final CustomAuthenticationFilter filter;
    private final CustomAuthenticationProvider provider;

    public ProjectConfig(@Lazy CustomAuthenticationFilter filter, CustomAuthenticationProvider provider) {
        this.filter = filter;
        this.provider = provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(provider);
        http.addFilterAt(filter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }


}
