package com.ketd.common.domain.coupon;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class SeckillSkuNoticeTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private Long skuId;
    private Long sessionId;
    private Date subcribeTime;
    private Date sendTime;
    private Integer noticeType;


}
