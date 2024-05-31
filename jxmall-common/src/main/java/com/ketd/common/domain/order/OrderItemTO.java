package com.ketd.common.domain.order;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class OrderItemTO  implements Serializable
{


    private Long id;
    private Long orderId;
    private String orderSn;
    private Long spuId;
    private String spuName;
    private String spuPic;
    private String spuBrand;
    private Long categoryId;
    private Long skuId;
    private String skuName;
    private String skuPic;
    private BigDecimal skuPrice;
    private Long skuQuantity;
    private String skuAttrsVals;
    private BigDecimal promotionAmount;
    private BigDecimal couponAmount;
    private BigDecimal integrationAmount;
    private BigDecimal realAmount;
    private BigDecimal weight;
    private Long giftIntegration;
    private Long giftGrowth;


}
