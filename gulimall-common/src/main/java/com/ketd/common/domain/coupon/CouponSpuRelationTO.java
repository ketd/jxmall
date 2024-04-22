package com.ketd.common.domain.coupon;


import lombok.Data;
import java.io.Serializable;


@Data
public class CouponSpuRelationTO  implements Serializable
{


    private Long id;
    private Long couponId;
    private Long spuId;
    private String spuName;


}
