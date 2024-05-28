package com.ketd.order.domain;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单项信息对象 oms_order_item
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_order_item")
@Data
public class OrderItem  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "order_id")
        @Schema(description =  "order_id")
        private Long orderId;

        @ExcelProperty(value = "order_sn")
        @Schema(description =  "order_sn")
        private String orderSn;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "spu_name")
        @Schema(description =  "spu_name")
        private String spuName;

        @ExcelProperty(value = "spu_pic")
        @Schema(description =  "spu_pic")
        private String spuPic;

        @ExcelProperty(value = "品牌")
        @Schema(description =  "品牌")
        private String spuBrand;

        @ExcelProperty(value = "商品分类id")
        @Schema(description =  "商品分类id")
        private Long categoryId;

        @ExcelProperty(value = "商品sku编号")
        @Schema(description =  "商品sku编号")
        private Long skuId;

        @ExcelProperty(value = "商品sku名字")
        @Schema(description =  "商品sku名字")
        private String skuName;

        @ExcelProperty(value = "商品sku图片")
        @Schema(description =  "商品sku图片")
        private String skuPic;

        @ExcelProperty(value = "商品sku价格")
        @Schema(description =  "商品sku价格")
        private BigDecimal skuPrice;

        @ExcelProperty(value = "商品购买的数量")
        @Schema(description =  "商品购买的数量")
        private Long skuQuantity;

        @ExcelProperty(value = "商品销售属性组合（JSON）")
        @Schema(description =  "商品销售属性组合（JSON）")
        private String skuAttrsVals;

        @ExcelProperty(value = "商品促销分解金额")
        @Schema(description =  "商品促销分解金额")
        private BigDecimal promotionAmount;

        @ExcelProperty(value = "优惠券优惠分解金额")
        @Schema(description =  "优惠券优惠分解金额")
        private BigDecimal couponAmount;

        @ExcelProperty(value = "积分优惠分解金额")
        @Schema(description =  "积分优惠分解金额")
        private BigDecimal integrationAmount;

        @ExcelProperty(value = "订单总分解金额")
        @Schema(description =  "订单总分解金额")
        private BigDecimal totalAmount;

        @ExcelProperty(value = "该商品经过优惠后的分解金额")
        @Schema(description =  "该商品经过优惠后的分解金额")
        private BigDecimal realAmount;

        @ExcelProperty(value = "赠送积分")
        @Schema(description =  "赠送积分")
        private Long giftIntegration;

        @ExcelProperty(value = "赠送成长值")
        @Schema(description =  "赠送成长值")
        private Long giftGrowth;


}
