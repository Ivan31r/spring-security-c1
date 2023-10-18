package com.example.springsecurityl22.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();

        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
       return new InMemoryClientRegistrationRepository(gitHubRegistration());
    }

    private ClientRegistration gitHubRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("487da928ea3e0ffb36d7")
                .clientSecret("66ad999cf45c4ca7d08ab3f0184a0f9ac05855f1")
                .build();
    }
}
