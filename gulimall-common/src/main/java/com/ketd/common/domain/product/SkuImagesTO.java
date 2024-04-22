package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class SkuImagesTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private String imgUrl;
    private Long imgSort;
    private Long defaultImg;


}
