package com.ketd.common.domain.coupon;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class SpuBoundsTO  implements Serializable
{


    private Long id;
    private Long spuId;
    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private Integer work;


}
