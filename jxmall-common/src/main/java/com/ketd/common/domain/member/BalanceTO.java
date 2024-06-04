package com.ketd.common.domain.member;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class BalanceTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private BigDecimal balance;
    private Date updatedAt;
    private Date createdAt;
    private Integer status;


}
