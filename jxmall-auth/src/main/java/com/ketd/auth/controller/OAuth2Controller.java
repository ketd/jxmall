package com.ketd.auth.controller;

import com.ketd.auth.server.OAuth2Service;
import com.ketd.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.controller
 * @Author: ketd
 * @CreateTime: 2024-05-16  23:32
 */
@Tag(name = "OAuth2")
@RestController
@RequestMapping("/auth/oauth2")
public class OAuth2Controller {
    @Autowired
    private OAuth2Service oAuth2Service;

    @Operation(summary = "Github授权")
    @GetMapping("/github")
    public Result<?> github(@RequestParam(value = "code") String code) {
        return oAuth2Service.github(code);
    }

    @Operation(summary = "Gitee授权")
    @GetMapping("/gitee")
    public Result<?> gitee(@RequestParam(value = "code") String code) {
        return oAuth2Service.gitee(code);
    }


}
