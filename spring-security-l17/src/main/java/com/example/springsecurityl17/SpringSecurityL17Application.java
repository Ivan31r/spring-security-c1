package com.example.springsecurityl17;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class SpringSecurityL17Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringSecurityL17Application.class, args);
		Console.main(args);
	}

}
