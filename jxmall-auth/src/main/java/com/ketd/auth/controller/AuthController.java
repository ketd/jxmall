package com.ketd.auth.controller;

import com.ketd.auth.server.AuthService;
import com.ketd.auth.vo.LoginVo;
import com.ketd.auth.vo.MemberRegisterVo;
import com.ketd.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.controller
 * @Author: ketd
 * @CreateTime: 2024-05-15  15:34
 */

@Tag(name = "用户注册中心")
@RestController
@RequestMapping("/auth/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Operation(summary = "用户注册控制器")
    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody MemberRegisterVo memberRegisterVo) {//json数据接收加@RequestBody注解
        return authService.register(memberRegisterVo);
    }

    @Operation(summary = "获取注册验证码")
    @PostMapping(value = "/getEmailCode")
    public Result<?> getEmailCode(@RequestParam(value = "email") String email) {

        return authService.sendMailCode(email);
    }

    @Operation(summary = "用户登录控制器")
    @PutMapping(value = "/login")
    public Result<?> Login( HttpServletRequest request,@RequestBody LoginVo loginVo) {
        return authService.login(request,loginVo);
    }




}
