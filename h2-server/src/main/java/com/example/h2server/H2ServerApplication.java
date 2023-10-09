package com.example.h2server;

import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2ServerApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(H2ServerApplication.class, args);
        Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "9092", "-tcpPassword", "password").start();
        Console.main(args);
    }

}
