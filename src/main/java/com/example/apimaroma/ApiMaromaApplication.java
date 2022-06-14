package com.example.apimaroma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMaromaApplication {

    private static Logger logger = LoggerFactory.getLogger(ApiMaromaApplication.class);
    public static void main(String[] args) {
        logger.info("Starting SpringBoot Server");
        SpringApplication.run(ApiMaromaApplication.class, args);
    }
}
