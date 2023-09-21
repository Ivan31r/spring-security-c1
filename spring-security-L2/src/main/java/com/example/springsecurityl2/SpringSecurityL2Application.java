package com.example.springsecurityl2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityL2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityL2Application.class, args);
    }

}
