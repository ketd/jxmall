package com.ketd.coupon.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 秒杀活动商品关联对象 sms_seckill_sku_relation
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_seckill_sku_relation")
@Data
public class SeckillSkuRelation  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "活动id")
        @Schema(description =  "活动id")
        private Long promotionId;

        @ExcelProperty(value = "活动场次id")
        @Schema(description =  "活动场次id")
        private Long promotionSessionId;

        @ExcelProperty(value = "商品id")
        @Schema(description =  "商品id")
        private Long skuId;

        @ExcelProperty(value = "秒杀价格")
        @Schema(description =  "秒杀价格")
        private Long seckillPrice;

        @ExcelProperty(value = "秒杀总量")
        @Schema(description =  "秒杀总量")
        private Long seckillCount;

        @ExcelProperty(value = "每人限购数量")
        @Schema(description =  "每人限购数量")
        private Long seckillLimit;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long seckillSort;


}
