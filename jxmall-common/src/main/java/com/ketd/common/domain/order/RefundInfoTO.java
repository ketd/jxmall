package com.ketd.common.domain.order;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class RefundInfoTO  implements Serializable
{


    private Long id;
    private Long orderReturnId;
    private BigDecimal refund;
    private String refundSn;
    private Integer refundStatus;
    private Long refundChannel;
    private String refundContent;


}
