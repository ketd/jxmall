package com.ketd.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd
 * @Author: ketd
 * @CreateTime: 2024-04-05  13:21
 */

@EnableCaching
@MapperScan("com.ketd.product.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.ketd.common"})
public class Product {
    public static void main(String[] args) {
        SpringApplication.run(Product.class, args);
    }
}