package com.example.springsecurityl28.controller;

import com.example.springsecurityl28.service.SimpleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SimpleController {

    private final SimpleService service;

    public SimpleController(SimpleService service) {
        this.service = service;
    }

    @GetMapping("/test1")
    public String test1() {
        return service.getTest1();
    }

    @GetMapping("/test2")
    public String test2() {
        return service.getTest2();
    }

    @GetMapping("/test4")
    public List<String> test4() {
        List<String> list = new ArrayList<>();
        list.add("john");
        list.add("bill");
        list.add("mary");

        return service.test4(list);
    }
}
