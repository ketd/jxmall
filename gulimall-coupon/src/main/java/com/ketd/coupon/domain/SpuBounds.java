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
 * 商品spu积分设置对象 sms_spu_bounds
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_spu_bounds")
@Data
public class SpuBounds  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private Long spuId;

        @ExcelProperty(value = "成长积分")
        @Schema(description =  "成长积分")
        private BigDecimal growBounds;

        @ExcelProperty(value = "购物积分")
        @Schema(description =  "购物积分")
        private BigDecimal buyBounds;

        @ExcelProperty(value = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
        @Schema(description =  "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
        private Integer work;


}
