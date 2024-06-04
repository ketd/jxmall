package com.ketd.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AliPayVo {

    //订单号
    private String outTraceNo;
    //订单金额
    private BigDecimal totalAmount;
    //订单名称
    private String subject;
    //支付宝交易号
    private String alipayTraceNo;
}
