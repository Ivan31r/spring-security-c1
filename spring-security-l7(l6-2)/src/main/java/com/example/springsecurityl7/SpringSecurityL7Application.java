package com.example.springsecurityl7;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL7Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringSecurityL7Application.class, args);
        Console.main(args);
    }

}
