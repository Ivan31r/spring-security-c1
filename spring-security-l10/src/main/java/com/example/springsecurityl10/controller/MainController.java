package com.example.springsecurityl10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    public static final String MAIN_HTML = "main.html";

    @GetMapping("/")
    public String main() {
        return MAIN_HTML;
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("*") //v1
    public String test() {
        return "TEST !";
    }
}
