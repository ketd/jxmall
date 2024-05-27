package com.ketd.common.domain.order;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class OrderOperateHistoryTO  implements Serializable
{


    private Long id;
    private Long orderId;
    private String operateMan;
    private Date createTime;
    private Long orderStatus;
    private String note;


}
