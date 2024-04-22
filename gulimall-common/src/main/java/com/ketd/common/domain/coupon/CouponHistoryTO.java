package com.ketd.common.domain.coupon;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class CouponHistoryTO  implements Serializable
{


    private Long id;
    private Long couponId;
    private Long memberId;
    private String memberNickName;
    private Integer getType;
    private Date createTime;
    private Integer useStateType;
    private Date useTime;
    private Long orderId;
    private Long orderSn;


}
