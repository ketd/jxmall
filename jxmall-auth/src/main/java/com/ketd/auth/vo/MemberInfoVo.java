package com.ketd.auth.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.vo
 * @Author: ketd
 * @CreateTime: 2024-05-17  17:42
 */
@Data
public class MemberInfoVo {


    @Schema(description =  "id")
    private Long id;

    @Schema(description =  "会员等级id")
    private Long levelId;

    @Schema(description =  "用户名")
    private String username;


    @Schema(description =  "昵称")
    private String nickname;

    @Schema(description =  "手机号码")
    private String mobile;

    @Schema(description =  "邮箱")
    private String email;

    @Schema(description =  "头像")
    private String header;

    @Schema(description =  "性别")
    private Long gender;

    @Schema(description =  "生日")
    private Date birth;

    @Schema(description =  "所在城市")
    private String city;

    @Schema(description =  "职业")
    private String job;

    @Schema(description =  "个性签名")
    private String sign;

    @Schema(description =  "积分")
    private Long integration;

    @Schema(description =  "成长值")
    private Long growth;

    @Schema(description =  "token")
    private String token;


}
