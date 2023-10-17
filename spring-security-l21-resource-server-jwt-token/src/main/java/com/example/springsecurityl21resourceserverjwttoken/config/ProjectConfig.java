package com.example.springsecurityl21resourceserverjwttoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                customizer -> customizer.jwt(
                        jwtCustomizer -> jwtCustomizer.decoder(decoder())));

        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public JwtDecoder decoder() {
        String keyValue = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
        SecretKey key = new SecretKeySpec(keyValue.getBytes(), 0, keyValue.getBytes().length, "AES");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
