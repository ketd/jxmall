package com.ketd.common.domain.order;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class OrderReturnReasonTO  implements Serializable
{


    private Long id;
    private String name;
    private Long sort;
    private Integer status;
    private Date createTime;


}
