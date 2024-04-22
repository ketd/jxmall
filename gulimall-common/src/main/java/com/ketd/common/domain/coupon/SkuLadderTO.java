package com.ketd.common.domain.coupon;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class SkuLadderTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private Long fullCount;
    private BigDecimal discount;
    private BigDecimal price;
    private Integer addOther;


}
