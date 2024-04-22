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
 * 优惠券与产品关联对象 sms_coupon_spu_relation
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_coupon_spu_relation")
@Data
public class CouponSpuRelation  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "优惠券id")
        @Schema(description =  "优惠券id")
        private Long couponId;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "spu_name")
        @Schema(description =  "spu_name")
        private String spuName;


}
