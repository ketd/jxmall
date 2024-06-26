package com.ketd.auth.server;

import com.ketd.auth.vo.LoginVo;
import com.ketd.auth.vo.MemberRegisterVo;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server.impl
 * @Author: ketd
 * @CreateTime: 2024-05-15  15:36
 */
public interface AuthService {

    Result<?> register(MemberRegisterVo memberRegisterVo);

    Result<?> sendMailCode(String email);

    Result<?> login(HttpServletRequest request, LoginVo loginVo);
}
