package com.ketd.common.domain.product;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class SkuInfoTO  implements Serializable
{


    private Long skuId;
    private Long spuId;
    private String skuName;
    private String skuDesc;
    private Long catalogId;
    private Long brandId;
    private String skuDefaultImg;
    private String skuTitle;
    private String skuSubtitle;
    private BigDecimal price;
    private Long saleCount;
    private String skuAttrValues;


}
