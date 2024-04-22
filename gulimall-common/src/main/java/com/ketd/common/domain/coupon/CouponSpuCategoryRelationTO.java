package com.ketd.common.domain.coupon;


import lombok.Data;
import java.io.Serializable;


@Data
public class CouponSpuCategoryRelationTO  implements Serializable
{


    private Long id;
    private Long couponId;
    private Long categoryId;
    private String categoryName;


}
