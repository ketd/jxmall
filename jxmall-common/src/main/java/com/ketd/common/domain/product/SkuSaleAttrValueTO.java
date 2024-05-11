package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class SkuSaleAttrValueTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private Long attrId;
    private String attrName;
    private String attrValue;
    private Long attrSort;


}
