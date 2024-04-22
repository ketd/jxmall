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
 * 商品阶梯价格对象 sms_sku_ladder
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_sku_ladder")
@Data
public class SkuLadder  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long skuId;

        @ExcelProperty(value = "满几件")
        @Schema(description =  "满几件")
        private Long fullCount;

        @ExcelProperty(value = "打几折")
        @Schema(description =  "打几折")
        private BigDecimal discount;

        @ExcelProperty(value = "折后价")
        @Schema(description =  "折后价")
        private BigDecimal price;

        @ExcelProperty(value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
        @Schema(description =  "是否叠加其他优惠[0-不可叠加，1-可叠加]")
        private Integer addOther;


}
