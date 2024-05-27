package com.ketd.order.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-27  21:54
 */
@Data
public class OrderItemVo implements Serializable {

    @Schema(description =  "id")
    private Long id;

    @Schema(description =  "order_id")
    private Long orderId;

    @Schema(description =  "order_sn")
    private String orderSn;

    @Schema(description =  "spu_id")
    private Long spuId;

    @Schema(description =  "spu_name")
    private String spuName;

    @Schema(description =  "spu_pic")
    private String spuPic;

    @Schema(description =  "品牌")
    private String spuBrand;

    @Schema(description =  "商品分类id")
    private Long categoryId;

    @Schema(description =  "商品sku编号")
    private Long skuId;

    @Schema(description =  "商品sku名字")
    private String skuName;

    @Schema(description =  "商品sku图片")
    private String skuPic;

    @Schema(description =  "商品sku价格")
    private BigDecimal skuPrice;

    @Schema(description =  "商品购买的数量")
    private Long skuQuantity;

    @Schema(description =  "商品销售属性组合（JSON）")
    private String skuAttrsVals;

    @Schema(description =  "商品促销分解金额")
    private BigDecimal promotionAmount;

    @Schema(description =  "优惠券优惠分解金额")
    private BigDecimal couponAmount;

    @Schema(description =  "积分优惠分解金额")
    private BigDecimal integrationAmount;

    @Schema(description =  "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    @Schema(description =  "赠送积分")
    private Long giftIntegration;

    @Schema(description =  "赠送成长值")
    private Long giftGrowth;
}
