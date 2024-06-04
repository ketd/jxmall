package com.ketd.member.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户余额明细对象 ums_balance_details
 *
 * @author ketd
 * @date 2024-06-04
 */

@TableName(value ="ums_balance_details")
@Data
public class BalanceDetails  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



    @TableId
    @ExcelIgnore
    @ExcelProperty(value = "${column.columnComment}")
    @Schema(description =  "${column.columnComment}")
    private Long id;

    @ExcelProperty(value = "会员")
    @Schema(description =  "会员")
    private Long memberId;

    @ExcelProperty(value = "交易的日期和时间")
    @Schema(description =  "交易的日期和时间")
    private Date transactionDate;

    @ExcelProperty(value = "交易的金额")
    @Schema(description =  "交易的金额")
    private BigDecimal transactionAmount;

    @ExcelProperty(value = "1-购物,2-充值,3-提现")
    @Schema(description =  "1-购物,2-充值,3-提现")
    private Integer transactionType;

    @ExcelProperty(value = "交易前的余额")
    @Schema(description =  "交易前的余额")
    private BigDecimal balanceBefore;

    @ExcelProperty(value = "交易后的余额")
    @Schema(description =  "交易后的余额")
    private BigDecimal balanceAfter;

    @ExcelProperty(value = "补充信息")
    @Schema(description =  "补充信息")
    private String note;

    @ExcelProperty(value = "订单id")
    @Schema(description =  "订单id")
    private Long orderId;

    @ExcelProperty(value = "订单号")
    @Schema(description =  "订单号")
    private String orderSn;


}
