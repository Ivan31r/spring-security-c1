package com.example.springsecurityl21authserverjwttoken.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                customizer -> customizer.opaqueToken(tokenCustomizer -> {
                    tokenCustomizer.introspectionUri("http://localhost:8080/oauth/check_token");
                    tokenCustomizer.introspectionClientCredentials("client1", "secret1");
                })
        );

        http.authorizeRequests().anyRequest().authenticated();
    }
}
