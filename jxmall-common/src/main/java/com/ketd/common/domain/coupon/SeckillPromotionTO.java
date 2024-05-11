package com.ketd.common.domain.coupon;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class SeckillPromotionTO  implements Serializable
{


    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;
    private Long status;
    private Date createTime;
    private Long userId;


}
