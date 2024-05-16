package com.ketd.auth.controller;

import com.ketd.auth.server.OAuth2Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.controller
 * @Author: ketd
 * @CreateTime: 2024-05-16  23:32
 */
@Tag(name = "用户注册中心")
@RestController
@RequestMapping("/auth/oauth")
public class OAuth2Controller {

    @Autowired
    private OAuth2Server  oAuth2Server;

    @PostMapping("/github")
    public ModelAndView authorize(@NotEmpty String code) {
        return oAuth2Server.github(code);
    }

}
