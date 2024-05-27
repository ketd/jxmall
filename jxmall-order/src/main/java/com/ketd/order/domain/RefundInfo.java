package com.ketd.order.domain;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 退款信息对象 oms_refund_info
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_refund_info")
@Data
public class RefundInfo  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "退款的订单")
        @Schema(description =  "退款的订单")
        private Long orderReturnId;

        @ExcelProperty(value = "退款金额")
        @Schema(description =  "退款金额")
        private BigDecimal refund;

        @ExcelProperty(value = "退款交易流水号")
        @Schema(description =  "退款交易流水号")
        private String refundSn;

        @ExcelProperty(value = "退款状态")
        @Schema(description =  "退款状态")
        private Integer refundStatus;

        @ExcelProperty(value = "退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
        @Schema(description =  "退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
        private Long refundChannel;

        @ExcelProperty(value = "退款内容")
        @Schema(description =  "退款内容")
        private String refundContent;


}
