package com.ketd.auth.vo;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.vo
 * @Author: ketd
 * @CreateTime: 2024-05-16  16:18
 */
@Data
public class LoginVo {

    public String email;
    private String mobile;
    public String password;
}
