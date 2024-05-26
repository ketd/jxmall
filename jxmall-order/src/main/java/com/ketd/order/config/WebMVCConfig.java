package com.ketd.order.config;


import com.ketd.order.Interceptors.LoginProtectedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: ketd
 * @time: 2023-10-22 10:07
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final LoginProtectedInterceptor loginProtectedInterceptor;

    @Autowired
    public WebMVCConfig(LoginProtectedInterceptor loginProtectedInterceptor) {
        this.loginProtectedInterceptor = loginProtectedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectedInterceptor)
                .addPathPatterns("/**")
        ;

    }
}
