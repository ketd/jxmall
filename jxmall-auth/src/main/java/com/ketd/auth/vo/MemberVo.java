package com.ketd.auth.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.member.vo
 * @Author: ketd
 * @CreateTime: 2024-05-08  15:31
 */
@Data
public class MemberVo {

    @Schema(description =  "用户名")
    private String username;

    @Schema(description =  "密码")
    private String password;

    @Schema(description =  "昵称")
    private String nickname;

    @Schema(description =  "手机号码")
    private String mobile;

    @Schema(description =  "邮箱")
    private String email;

    @Schema(description =  "验证码")
    private String Code;
}
