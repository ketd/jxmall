package com.ketd.common.domain.ware;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class WareOrderTaskTO  implements Serializable
{


    private Long id;
    private Long orderId;
    private String orderSn;
    private String consignee;
    private String consigneeTel;
    private String deliveryAddress;
    private String orderComment;
    private Integer paymentWay;
    private Long taskStatus;
    private String orderBody;
    private String trackingNo;
    private Date createTime;
    private Long wareId;
    private String taskComment;


}
