package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class SpuImagesTO  implements Serializable
{


    private Long id;
    private Long spuId;
    private String imgName;
    private String imgUrl;
    private Long imgSort;
    private Long defaultImg;


}
