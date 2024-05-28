package com.ketd.common.domain.product;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class SpuInfoTO  implements Serializable
{


    private Long id;
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private String brandName;
    private BigDecimal weight;
    private Long publishStatus;
    private Date createTime;
    private Date updateTime;


}
