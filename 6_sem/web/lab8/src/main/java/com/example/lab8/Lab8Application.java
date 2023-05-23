package com.example.lab8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.lab8.Entities")
public class Lab8Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab8Application.class, args);
    }

}
