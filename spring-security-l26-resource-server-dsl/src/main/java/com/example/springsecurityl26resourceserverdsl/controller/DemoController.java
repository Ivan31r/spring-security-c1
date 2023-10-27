package com.example.springsecurityl26resourceserverdsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "DEMO_NEMO_EGO";
    }
}
