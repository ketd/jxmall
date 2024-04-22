package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class AttrAttrgroupRelationTO  implements Serializable
{


    private Long id;
    private Long attrId;
    private Long attrGroupId;
    private Long attrSort;


}
