package com.example.springsecurityl7.security.manager;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/*
По хорошему данный менеджер должен быть реализован аналогично org.springframework.security.authentication.AuthenticationManager,
например как ProviderManager со своим списком auth-провайдеров.
   */
@Component
public class TokenManager {

    private Set<String> tokens = new HashSet<>();


    public void add(String token) {
        tokens.add(token);
    }

    public boolean contains(String token) {
        return tokens.contains(token);
    }
}
