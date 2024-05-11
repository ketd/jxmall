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
 * 优惠券分类关联对象 sms_coupon_spu_category_relation
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_coupon_spu_category_relation")
@Data
public class CouponSpuCategoryRelation  implements Serializable
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

        @ExcelProperty(value = "产品分类id")
        @Schema(description =  "产品分类id")
        private Long categoryId;

        @ExcelProperty(value = "产品分类名称")
        @Schema(description =  "产品分类名称")
        private String categoryName;


}
