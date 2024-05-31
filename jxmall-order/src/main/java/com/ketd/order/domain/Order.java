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
 * 订单对象 oms_order
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value = "oms_order")
@Data
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @TableId
    @ExcelIgnore
    @ExcelProperty(value = "id")
    @Schema(description = "id")
    private Long id;

    @ExcelProperty(value = "member_id")
    @Schema(description = "member_id")
    private Long memberId;

    @ExcelProperty(value = "订单号")
    @Schema(description = "订单号")
    private String orderSn;

    @ExcelProperty(value = "使用的优惠券")
    @Schema(description = "使用的优惠券")
    private Long couponId;

    @ExcelProperty(value = "create_time")
    @Schema(description = "create_time")
    private Date createTime;

    @ExcelProperty(value = "用户名")
    @Schema(description = "用户名")
    private String memberUsername;

    @ExcelProperty(value = "订单总额")
    @Schema(description = "订单总额")
    private BigDecimal totalAmount;

    @ExcelProperty(value = "应付总额")
    @Schema(description = "应付总额")
    private BigDecimal payAmount;

    @ExcelProperty(value = "运费金额")
    @Schema(description = "运费金额")
    private BigDecimal freightAmount;

    @ExcelProperty(value = "商品重量")
    @Schema(description = "商品重量")
    private BigDecimal weight;

    @ExcelProperty(value = "促销优化金额（促销价、满减、阶梯价）")
    @Schema(description = "促销优化金额（促销价、满减、阶梯价）")
    private BigDecimal promotionAmount;

    @ExcelProperty(value = "积分抵扣金额")
    @Schema(description = "积分抵扣金额")
    private BigDecimal integrationAmount;

    @ExcelProperty(value = "优惠券抵扣金额")
    @Schema(description = "优惠券抵扣金额")
    private BigDecimal couponAmount;

    @ExcelProperty(value = "后台调整订单使用的折扣金额")
    @Schema(description = "后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;

    @ExcelProperty(value = "支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】")
    @Schema(description = "支付方式【1-&gt;支付宝；2-&gt;微信；3-&gt;银联； 4-&gt;货到付款；】")
    private Integer payType;

    @ExcelProperty(value = "订单来源[0-&gt;PC订单；1-&gt;app订单]")
    @Schema(description = "订单来源[0-&gt;PC订单；1-&gt;app订单]")
    private Integer sourceType;

    @ExcelProperty(value = "订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】")
    @Schema(description = "订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】")
    private Integer status;

    @ExcelProperty(value = "物流公司(配送方式)")
    @Schema(description = "物流公司(配送方式)")
    private String deliveryCompany;

    @ExcelProperty(value = "物流单号")
    @Schema(description = "物流单号")
    private String deliverySn;

    @ExcelProperty(value = "自动确认时间（天）")
    @Schema(description = "自动确认时间（天）")
    private Integer autoConfirmDay;

    @ExcelProperty(value = "可以获得的积分")
    @Schema(description = "可以获得的积分")
    private Long integration;

    @ExcelProperty(value = "可以获得的成长值")
    @Schema(description = "可以获得的成长值")
    private Long growth;

    @ExcelProperty(value = "发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]")
    @Schema(description = "发票类型[0-&gt;不开发票；1-&gt;电子发票；2-&gt;纸质发票]")
    private Integer billType;

    @ExcelProperty(value = "发票抬头")
    @Schema(description = "发票抬头")
    private String billHeader;

    @ExcelProperty(value = "发票内容")
    @Schema(description = "发票内容")
    private String billContent;

    @ExcelProperty(value = "收票人电话")
    @Schema(description = "收票人电话")
    private String billReceiverPhone;

    @ExcelProperty(value = "收票人邮箱")
    @Schema(description = "收票人邮箱")
    private String billReceiverEmail;

    @ExcelProperty(value = "收货人姓名")
    @Schema(description = "收货人姓名")
    private String receiverName;

    @ExcelProperty(value = "收货人电话")
    @Schema(description = "收货人电话")
    private String receiverPhone;

    @ExcelProperty(value = "收货人邮编")
    @Schema(description = "收货人邮编")
    private String receiverPostCode;

    @ExcelProperty(value = "省份/直辖市")
    @Schema(description = "省份/直辖市")
    private String receiverProvince;

    @ExcelProperty(value = "城市")
    @Schema(description = "城市")
    private String receiverCity;

    @ExcelProperty(value = "区")
    @Schema(description = "区")
    private String receiverRegion;

    @ExcelProperty(value = "详细地址")
    @Schema(description = "详细地址")
    private String receiverDetailAddress;

    @ExcelProperty(value = "订单备注")
    @Schema(description = "订单备注")
    private String note;

    @ExcelProperty(value = "确认收货状态[0-&gt;未确认；1-&gt;已确认]")
    @Schema(description = "确认收货状态[0-&gt;未确认；1-&gt;已确认]")
    private Integer confirmStatus;

    @ExcelProperty(value = "删除状态【0-&gt;未删除；1-&gt;已删除】")
    @Schema(description = "删除状态【0-&gt;未删除；1-&gt;已删除】")
    private Integer deleteStatus;

    @ExcelProperty(value = "下单时使用的积分")
    @Schema(description = "下单时使用的积分")
    private Long useIntegration;

    @ExcelProperty(value = "支付时间")
    @Schema(description = "支付时间")
    private Date paymentTime;

    @ExcelProperty(value = "发货时间")
    @Schema(description = "发货时间")
    private Date deliveryTime;

    @ExcelProperty(value = "确认收货时间")
    @Schema(description = "确认收货时间")
    private Date receiveTime;

    @ExcelProperty(value = "评价时间")
    @Schema(description = "评价时间")
    private Date commentTime;

    @ExcelProperty(value = "修改时间")
    @Schema(description = "修改时间")
    private Date modifyTime;


}
