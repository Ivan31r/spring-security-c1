package com.example.springsecurityl8;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL8Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringSecurityL8Application.class, args);
        Console.main(args);
    }

}
