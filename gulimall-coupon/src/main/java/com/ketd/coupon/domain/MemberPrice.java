package com.ketd.coupon.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品会员价格对象 sms_member_price
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_member_price")
@Data
public class MemberPrice  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "sku_id")
        @Schema(description =  "sku_id")
        private Long skuId;

        @ExcelProperty(value = "会员等级id")
        @Schema(description =  "会员等级id")
        private Long memberLevelId;

        @ExcelProperty(value = "会员等级名")
        @Schema(description =  "会员等级名")
        private String memberLevelName;

        @ExcelProperty(value = "会员对应价格")
        @Schema(description =  "会员对应价格")
        private BigDecimal memberPrice;

        @ExcelProperty(value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
        @Schema(description =  "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
        private Integer addOther;


}
