package com.ketd.cart.Interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketd.cart.util.JXJwtTokenUtil;
import com.ketd.common.result.Result;
import com.ketd.common.result.ResultCodeEnum;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description:
 * 登录包含拦截器，检查请求头是否有效token
 * 有效放行
 * 无效拦截
 * @author: ketd
 * @time: 2023-10-22 9:58
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JXJwtTokenUtil jXJwtTokenUtil;

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token;
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                token = Objects.requireNonNull(Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("auth_token")).findFirst().orElse(null)).getValue();
            } else {
                return false;
            }

            // 判断token是否过期
            boolean isExpired = jXJwtTokenUtil.isTokenExpired(token);

            if (!isExpired) {
                Long userId = jXJwtTokenUtil.getUserIdFromToken(token);
                threadLocal.set(userId);
                return true;
            }

            Result<?> result = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return false;
        } catch (Exception e) {
            threadLocal.remove();
            throw e;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
    }
}
