package com.example.backendcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.entity")
@ComponentScan(basePackages = {
        "com.example.entity",
        "com.example.controller",
        "com.example.repository"
})
@EnableJpaRepositories("com.example.repository")
@SpringBootApplication
public class BackendCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendCaseApplication.class, args);
    }

}
