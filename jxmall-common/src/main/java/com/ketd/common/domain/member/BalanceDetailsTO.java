package com.ketd.common.domain.member;


import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class BalanceDetailsTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private Date transactionDate;
    private BigDecimal transactionAmount;
    private Integer transactionType;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private String note;
    private Long orderId;
    private String orderSn;


}
