package com.kurdestan.lezzoo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LezzooApp {

    public static void main(String[] args) {
        SpringApplication.run(LezzooApp.class);
    }
}