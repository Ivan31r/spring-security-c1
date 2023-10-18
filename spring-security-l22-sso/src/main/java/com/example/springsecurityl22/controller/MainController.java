package com.example.springsecurityl22.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public static final String MAIN_HTML = "main.html";

    @GetMapping()
    public String mainPage(){
        return MAIN_HTML;
    }

}
