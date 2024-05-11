package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class ProductAttrValueTO  implements Serializable
{


    private Long id;
    private Long spuId;
    private Long attrId;
    private String attrName;
    private String attrValue;
    private Long attrSort;
    private Long quickShow;


}
