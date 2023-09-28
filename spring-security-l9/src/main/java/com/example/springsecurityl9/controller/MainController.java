package com.example.springsecurityl9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    public static final String MAIN_HTML = "main.html";

    @GetMapping("/")
    public String main() {
        return MAIN_HTML;
    }

    @PostMapping("/change")
    public String doSmth() {
        System.out.println("=[");
        return MAIN_HTML;
    }

    @PostMapping("/csrfdizabled")
    public String csrfdizabled() {
        System.out.println("Inside csrfdizabled");
        return MAIN_HTML;
    }
}
