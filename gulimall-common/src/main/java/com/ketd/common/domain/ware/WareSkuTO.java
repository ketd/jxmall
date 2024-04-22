package com.ketd.common.domain.ware;


import lombok.Data;
import java.io.Serializable;


@Data
public class WareSkuTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private Long wareId;
    private Long stock;
    private String skuName;
    private Long stockLocked;


}
