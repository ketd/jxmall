package com.ketd.common.domain.order;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class OrderTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private String orderSn;
    private Long couponId;
    private Date createTime;
    private String memberUsername;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private BigDecimal freightAmount;
    private BigDecimal promotionAmount;
    private BigDecimal integrationAmount;
    private BigDecimal couponAmount;
    private BigDecimal discountAmount;
    private Long payType;
    private Long sourceType;
    private Long status;
    private String deliveryCompany;
    private String deliverySn;
    private Long autoConfirmDay;
    private Long integration;
    private Long growth;
    private Long billType;
    private String billHeader;
    private String billContent;
    private String billReceiverPhone;
    private String billReceiverEmail;
    private String receiverName;
    private String receiverPhone;
    private String receiverPostCode;
    private String receiverProvince;
    private String receiverCity;
    private String receiverRegion;
    private String receiverDetailAddress;
    private String note;
    private Long confirmStatus;
    private Long deleteStatus;
    private Long useIntegration;
    private Date paymentTime;
    private Date deliveryTime;
    private Date receiveTime;
    private Date commentTime;
    private Date modifyTime;


}
