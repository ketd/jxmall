package com.ketd;

import com.ketd.config.GulimallElasticSearchConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@MapperScan("com.ketd.ware.dao")
@EnableDiscoveryClient
public class Search {
    public static void main(String[] args) {

        SpringApplication.run(Search.class, args);
    }
   /* @Autowired
    public GulimallElasticSearchConfig gulimallElasticSearchConfig;*/
}