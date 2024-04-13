package com.ketd.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@MapperScan("com.ketd.ware.dao")
@EnableDiscoveryClient
public class Ware {
    public static void main(String[] args) {
        SpringApplication.run(Ware.class, args);
    }
}