package com.szalai.funqtion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FunqtionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunqtionApplication.class, args);
    }
}
