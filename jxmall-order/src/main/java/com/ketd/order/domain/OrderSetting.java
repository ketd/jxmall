package com.ketd.order.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单配置信息对象 oms_order_setting
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_order_setting")
@Data
public class OrderSetting  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "秒杀订单超时关闭时间(分)")
        @Schema(description =  "秒杀订单超时关闭时间(分)")
        private Long flashOrderOvertime;

        @ExcelProperty(value = "正常订单超时时间(分)")
        @Schema(description =  "正常订单超时时间(分)")
        private Long normalOrderOvertime;

        @ExcelProperty(value = "发货后自动确认收货时间（天）")
        @Schema(description =  "发货后自动确认收货时间（天）")
        private Long confirmOvertime;

        @ExcelProperty(value = "自动完成交易时间，不能申请退货（天）")
        @Schema(description =  "自动完成交易时间，不能申请退货（天）")
        private Long finishOvertime;

        @ExcelProperty(value = "订单完成后自动好评时间（天）")
        @Schema(description =  "订单完成后自动好评时间（天）")
        private Long commentOvertime;

        @ExcelProperty(value = "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
        @Schema(description =  "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
        private Long memberLevel;


}
