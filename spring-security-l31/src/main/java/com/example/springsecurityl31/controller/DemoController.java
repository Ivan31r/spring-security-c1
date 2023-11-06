package com.example.springsecurityl31.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public Mono<String> demo() {
        Mono<Authentication> authenticationMono = ReactiveSecurityContextHolder.getContext().map(securityContext -> securityContext.getAuthentication());
        return authenticationMono.map(authentication -> "Hello " + authentication.getName());
    }
}
