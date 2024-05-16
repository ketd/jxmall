package com.ketd.auth.server.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.ketd.auth.server.OAuth2Server;
import com.ketd.auth.util.HttpClientUtils;
import com.ketd.auth.vo.GithubInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server.impl
 * @Author: ketd
 * @CreateTime: 2024-05-16  23:33
 */
@Service
public class OAuth2ServerImpl implements OAuth2Server {






    @Override
    public ModelAndView github(String code) {
        HttpRequest  httpRequest =   HttpRequest
                .get("https://api.github.com/user/repos")
                .header("Authorization","token "+code);
        //GithubInfoVo  githubInfoVo =
        return null;
    }
}
