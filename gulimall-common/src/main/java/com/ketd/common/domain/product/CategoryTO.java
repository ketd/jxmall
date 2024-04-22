package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class CategoryTO  implements Serializable
{


    private Long catId;
    private String name;
    private Long parentCid;
    private Long catLevel;
    private Long showStatus;
    private Long sort;
    private String icon;
    private String productUnit;
    private Long productCount;


}
