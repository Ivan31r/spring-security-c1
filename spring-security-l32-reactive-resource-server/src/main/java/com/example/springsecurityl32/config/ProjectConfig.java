package com.example.springsecurityl32.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity.authorizeExchange()
                .anyExchange()
                .authenticated()
                .and().oauth2ResourceServer()
                .jwt(jwtSpec -> jwtSpec.jwkSetUri("auth server URL (it can be KeyCloak)"))
                .and().build();
    }
}
