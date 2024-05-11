package com.ketd.common.domain.coupon;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class CouponTO  implements Serializable
{


    private Long id;
    private Integer couponType;
    private String couponImg;
    private String couponName;
    private Long num;
    private BigDecimal amount;
    private Long perLimit;
    private BigDecimal minPoint;
    private Date startTime;
    private Date endTime;
    private Integer useType;
    private String note;
    private Long publishCount;
    private Long useCount;
    private Long receiveCount;
    private Date enableStartTime;
    private Date enableEndTime;
    private String code;
    private Integer memberLevel;
    private Integer publish;


}
