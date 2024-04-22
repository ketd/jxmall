package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class AttrGroupTO  implements Serializable
{


    private Long attrGroupId;
    private String attrGroupName;
    private Long sort;
    private String descript;
    private String icon;
    private Long catelogId;


}
