package com.codecool.csepdo.funnyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FunnyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunnyServiceApplication.class, args);
    }

}
