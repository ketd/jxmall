package com.ketd.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.product.config
 * @Author: ketd
 * @CreateTime: 2024-05-14  20:45
 */
@Configuration
public class MyTreadConfig {

    @Bean
    public ThreadPoolExecutor  threadPoolExecutor(){
        return new ThreadPoolExecutor(
                50,
                200,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
