package com.ketd.common.domain.ware;


import lombok.Data;
import java.io.Serializable;


@Data
public class WareOrderTaskDetailTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private String skuName;
    private Long skuNum;
    private Long taskId;


}
