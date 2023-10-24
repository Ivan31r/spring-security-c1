package com.example.springsecurityl24.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO!";
    }

    @GetMapping("/ciao")
    public String ciao() {
        return "CIAO!";
    }

    @PostMapping("/hola")
    public String hola() {
        return "HOLA!";
    }
}
