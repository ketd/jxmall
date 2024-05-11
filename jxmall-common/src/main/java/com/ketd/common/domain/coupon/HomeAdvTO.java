package com.ketd.common.domain.coupon;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class HomeAdvTO  implements Serializable
{


    private Long id;
    private String name;
    private String pic;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Long clickCount;
    private String url;
    private String note;
    private Long sort;
    private Long publisherId;
    private Long authId;


}
