package com.ketd.order.domain;

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
 * 支付信息对象 oms_payment_info
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_payment_info")
@Data
public class PaymentInfo  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "订单号（对外业务号）")
        @Schema(description =  "订单号（对外业务号）")
        private String orderSn;

        @ExcelProperty(value = "订单id")
        @Schema(description =  "订单id")
        private Long orderId;

        @ExcelProperty(value = "支付宝交易流水号")
        @Schema(description =  "支付宝交易流水号")
        private String alipayTradeNo;

        @ExcelProperty(value = "支付总金额")
        @Schema(description =  "支付总金额")
        private BigDecimal totalAmount;

        @ExcelProperty(value = "交易内容")
        @Schema(description =  "交易内容")
        private String subject;

        @ExcelProperty(value = "支付状态")
        @Schema(description =  "支付状态")
        private String paymentStatus;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;

        @ExcelProperty(value = "确认时间")
        @Schema(description =  "确认时间")
        private Date confirmTime;

        @ExcelProperty(value = "回调内容")
        @Schema(description =  "回调内容")
        private String callbackContent;

        @ExcelProperty(value = "回调时间")
        @Schema(description =  "回调时间")
        private Date callbackTime;


}
