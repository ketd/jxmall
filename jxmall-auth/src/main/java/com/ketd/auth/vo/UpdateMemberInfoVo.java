package com.ketd.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.vo
 * @Author: ketd
 * @CreateTime: 2024-05-21  17:43
 */
@Data
public class UpdateMemberInfoVo implements Serializable {


    @Schema(description =  "昵称")
    private String nickname;

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

}
