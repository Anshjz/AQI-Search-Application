package com.example.aqisearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AqisearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(AqisearchApplication.class, args);
    }
}

