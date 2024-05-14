package com.ketd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThirdParty {
    public static void main(String[] args) {
        SpringApplication.run(ThirdParty.class, args);
    }
}