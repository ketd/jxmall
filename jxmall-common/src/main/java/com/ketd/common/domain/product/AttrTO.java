package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class AttrTO  implements Serializable
{


    private Long attrId;
    private String attrName;
    private Long searchType;
    private Long valueType;
    private String icon;
    private String valueSelect;
    private Long attrType;
    private Long enable;
    private Long catelogId;
    private Long showDesc;


}
