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
 * 会员登录记录对象 ums_member_login_log
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_login_log")
@Data
public class MemberLoginLog  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "member_id")
        @Schema(description =  "member_id")
        private Long memberId;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;

        @ExcelProperty(value = "ip")
        @Schema(description =  "ip")
        private String ip;

        @ExcelProperty(value = "城市")
        @Schema(description =  "城市")
        private String city;

        @ExcelProperty(value = "登录类型[1-web，2-app]")
        @Schema(description =  "登录类型[1-web，2-app]")
        private Integer loginType;


}
