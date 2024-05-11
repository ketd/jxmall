package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class CategoryBrandRelationTO  implements Serializable
{


    private Long id;
    private Long brandId;
    private Long catelogId;
    private String brandName;
    private String catelogName;


}
