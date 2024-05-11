package com.ketd.common.domain.ware;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class PurchaseTO  implements Serializable
{


    private Long id;
    private Long assigneeId;
    private String assigneeName;
    private String phone;
    private Long priority;
    private Long status;
    private Long wareId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;


}
