package com.ketd.auth.server;

import com.ketd.auth.vo.LoginVo;
import com.ketd.auth.vo.MemberVo;
import com.ketd.common.result.Result;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server.impl
 * @Author: ketd
 * @CreateTime: 2024-05-15  15:36
 */
public interface AuthServer {

    Result<?> register(MemberVo memberVo);

    Result<?> sendMailCode(String email);

    Result<?> login(LoginVo loginVo);
}
