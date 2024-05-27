package com.ketd.common.domain.order;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class PaymentInfoTO  implements Serializable
{


    private Long id;
    private String orderSn;
    private Long orderId;
    private String alipayTradeNo;
    private BigDecimal totalAmount;
    private String subject;
    private String paymentStatus;
    private Date createTime;
    private Date confirmTime;
    private String callbackContent;
    private Date callbackTime;


}
