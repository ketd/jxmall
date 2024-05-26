package com.ketd.member.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 单点登录用户信息关联对象 ums_member_social
 *
 * @author ketd
 * @date 2024-05-17
 */

@TableName(value = "ums_member_social")
@Data
public class MemberSocial implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @TableId
    @ExcelIgnore
    @ExcelProperty(value = "id")
    @Schema(description = "id")
    private Long Id;

    @ExcelProperty(value = "用户id")
    @Schema(description = "用户id")
    private Long memberId;

    @ExcelProperty(value = "社交账号uid")
    @Schema(description = "社交账号uid")
    private String socialUid;

    @ExcelProperty(value = "token")
    @Schema(description = "token")
    private String accessToken;

    @ExcelProperty(value = "过期时间")
    @Schema(description = "过期时间")
    private Long exporesIn;

    @ExcelProperty(value = "头像地址")
    @Schema(description = "头像地址")
    private String avatarUrl;

    @ExcelProperty(value = "昵称")
    @Schema(description = "昵称")
    private String name;

    @ExcelProperty(value = "登录平台网站类型")
    @Schema(description = "登录平台网站类型")
    private Integer type;


}
