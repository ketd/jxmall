package com.ketd.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd
 * @Author: ketd
 * @CreateTime: 2024-04-05  13:21
 */
@SpringBootApplication
@MapperScan("com.ketd.product.mapper")
@EnableDiscoveryClient
public class Product {
    public static void main(String[] args) {
        SpringApplication.run(Product.class, args);
    }
}