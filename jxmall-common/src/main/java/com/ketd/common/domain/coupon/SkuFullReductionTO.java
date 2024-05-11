package com.ketd.common.domain.coupon;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class SkuFullReductionTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer addOther;


}
