package com.example.springsecurityl19resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${key}")
    private String keyVerifier;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        var converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(keyVerifier);
        return converter;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }
}
