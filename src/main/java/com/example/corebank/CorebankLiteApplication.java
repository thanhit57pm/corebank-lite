package com.example.corebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

@SpringBootApplication
public class CorebankLiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorebankLiteApplication.class, args);
    }

}
