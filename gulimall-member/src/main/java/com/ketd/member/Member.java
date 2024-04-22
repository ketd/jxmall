package com.ketd.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.ketd.member.mapper")
@EnableDiscoveryClient
@EnableTransactionManagement
public class Member {
    public static void main(String[] args) {
        SpringApplication.run(Member.class, args);
    }
}