package com.example.springsecurityl27.controller;

import com.example.springsecurityl27.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{username}")
    public List<String> fingProductsForUser(@PathVariable String username) {
        return productService.findProductsForUser(username);
    }
}
