package com.ketd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.ketd.common"})
@SpringBootApplication
@EnableDiscoveryClient
public class ThirdParty {
    public static void main(String[] args) {
        SpringApplication.run(ThirdParty.class, args);
    }
}