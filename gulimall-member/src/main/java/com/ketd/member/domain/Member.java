package com.ketd.member.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 会员对象 ums_member
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member")
@Data
public class Member  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "会员等级id")
        @Schema(description =  "会员等级id")
        private Long levelId;

        @ExcelProperty(value = "用户名")
        @Schema(description =  "用户名")
        private String username;

        @ExcelProperty(value = "密码")
        @Schema(description =  "密码")
        private String password;

        @ExcelProperty(value = "昵称")
        @Schema(description =  "昵称")
        private String nickname;

        @ExcelProperty(value = "手机号码")
        @Schema(description =  "手机号码")
        private String mobile;

        @ExcelProperty(value = "邮箱")
        @Schema(description =  "邮箱")
        private String email;

        @ExcelProperty(value = "头像")
        @Schema(description =  "头像")
        private String header;

        @ExcelProperty(value = "性别")
        @Schema(description =  "性别")
        private Long gender;

        @ExcelProperty(value = "生日")
        @Schema(description =  "生日")
        private Date birth;

        @ExcelProperty(value = "所在城市")
        @Schema(description =  "所在城市")
        private String city;

        @ExcelProperty(value = "职业")
        @Schema(description =  "职业")
        private String job;

        @ExcelProperty(value = "个性签名")
        @Schema(description =  "个性签名")
        private String sign;

        @ExcelProperty(value = "用户来源")
        @Schema(description =  "用户来源")
        private Long sourceType;

        @ExcelProperty(value = "积分")
        @Schema(description =  "积分")
        private Long integration;

        @ExcelProperty(value = "成长值")
        @Schema(description =  "成长值")
        private Long growth;

        @ExcelProperty(value = "启用状态")
        @Schema(description =  "启用状态")
        private Long status;

        @ExcelProperty(value = "注册时间")
        @Schema(description =  "注册时间")
        private Date createTime;


}
