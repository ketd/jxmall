package com.ketd.common.domain.coupon;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class SeckillSessionTO  implements Serializable
{


    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Date createTime;


}
