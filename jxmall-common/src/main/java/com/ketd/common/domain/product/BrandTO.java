package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class BrandTO  implements Serializable
{


    private Long brandId;
    private String name;
    private String logo;
    private String descript;
    private Long showStatus;
    private String firstLetter;
    private Long sort;


}
