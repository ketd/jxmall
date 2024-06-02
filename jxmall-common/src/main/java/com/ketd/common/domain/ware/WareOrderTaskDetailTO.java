package com.ketd.common.domain.ware;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.io.Serializable;


@Data
public class WareOrderTaskDetailTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private String skuName;
    private Integer skuNum;
    private Long taskId;
    private Long wareId;
    private Integer lockStatus;


}
