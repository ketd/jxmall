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
 * 商品满减信息对象 sms_sku_full_reduction
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_sku_full_reduction")
@Data
public class SkuFullReduction  implements Serializable
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

        @ExcelProperty(value = "满多少")
        @Schema(description =  "满多少")
        private BigDecimal fullPrice;

        @ExcelProperty(value = "减多少")
        @Schema(description =  "减多少")
        private BigDecimal reducePrice;

        @ExcelProperty(value = "是否参与其他优惠")
        @Schema(description =  "是否参与其他优惠")
        private Integer addOther;


}
