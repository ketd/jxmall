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
 * 会员收货地址对象 ums_member_receive_address
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_receive_address")
@Data
public class MemberReceiveAddress  implements Serializable
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

        @ExcelProperty(value = "收货人姓名")
        @Schema(description =  "收货人姓名")
        private String name;

        @ExcelProperty(value = "电话")
        @Schema(description =  "电话")
        private String phone;

        @ExcelProperty(value = "邮政编码")
        @Schema(description =  "邮政编码")
        private String postCode;

        @ExcelProperty(value = "省份/直辖市")
        @Schema(description =  "省份/直辖市")
        private String province;

        @ExcelProperty(value = "城市")
        @Schema(description =  "城市")
        private String city;

        @ExcelProperty(value = "区")
        @Schema(description =  "区")
        private String region;

        @ExcelProperty(value = "详细地址(街道)")
        @Schema(description =  "详细地址(街道)")
        private String detailAddress;

        @ExcelProperty(value = "省市区代码")
        @Schema(description =  "省市区代码")
        private String areacode;

        @ExcelProperty(value = "是否默认")
        @Schema(description =  "是否默认")
        private Integer defaultStatus;


}
