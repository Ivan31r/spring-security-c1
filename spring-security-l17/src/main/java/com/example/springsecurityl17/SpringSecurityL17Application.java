package com.example.springsecurityl16;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL16Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringSecurityL16Application.class, args);
		Console.main(args);
	}

}
