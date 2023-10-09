package com.example.springsecurityl15r;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL15Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringSecurityL15Application.class, args);
//        Console.main(args);
    }

}
