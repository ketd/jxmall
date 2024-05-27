package com.ketd.common.domain.order;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class OrderReturnApplyTO  implements Serializable
{


    private Long id;
    private Long orderId;
    private Long skuId;
    private String orderSn;
    private Date createTime;
    private String memberUsername;
    private BigDecimal returnAmount;
    private String returnName;
    private String returnPhone;
    private Integer status;
    private Date handleTime;
    private String skuImg;
    private String skuName;
    private String skuBrand;
    private String skuAttrsVals;
    private Long skuCount;
    private BigDecimal skuPrice;
    private BigDecimal skuRealPrice;
    private String reason;
    private String description述;
    private String descPics;
    private String handleNote;
    private String handleMan;
    private String receiveMan;
    private Date receiveTime;
    private String receiveNote;
    private String receivePhone;
    private String companyAddress;


}
