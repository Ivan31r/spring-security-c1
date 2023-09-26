package com.example.springsecurityl6;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL6Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringSecurityL6Application.class, args);
        Console.main(args);
    }

}
