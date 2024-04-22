package com.ketd.common.domain.coupon;


import lombok.Data;
import java.io.Serializable;


@Data
public class SeckillSkuRelationTO  implements Serializable
{


    private Long id;
    private Long promotionId;
    private Long promotionSessionId;
    private Long skuId;
    private Long seckillPrice;
    private Long seckillCount;
    private Long seckillLimit;
    private Long seckillSort;


}
