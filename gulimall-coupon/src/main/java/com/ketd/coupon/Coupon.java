package com.ketd.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.ketd.coupon.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
public class Coupon {
    public static void main(String[] args) {
        SpringApplication.run(Coupon.class, args);
    }
}