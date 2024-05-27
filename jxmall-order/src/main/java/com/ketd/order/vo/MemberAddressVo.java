package com.ketd.order.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-27  21:43
 */
@Data
public class MemberAddressVo implements Serializable {

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

    @Schema(description =  "区")
    private String region;

    @Schema(description =  "详细地址(街道)")
    private String detailAddress;

    @Schema(description =  "省市区代码")
    private String areacode;

    @Schema(description =  "是否默认")
    private Integer defaultStatus;
}
