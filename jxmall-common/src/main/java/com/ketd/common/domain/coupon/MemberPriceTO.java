package com.ketd.common.domain.coupon;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class MemberPriceTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private Long memberLevelId;
    private String memberLevelName;
    private BigDecimal memberPrice;
    private Integer addOther;


}
