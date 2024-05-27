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
 * 订单退货申请对象 oms_order_return_apply
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_order_return_apply")
@Data
public class OrderReturnApply  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "order_id")
        @Schema(description =  "order_id")
        private Long orderId;

        @ExcelProperty(value = "退货商品id")
        @Schema(description =  "退货商品id")
        private Long skuId;

        @ExcelProperty(value = "订单编号")
        @Schema(description =  "订单编号")
        private String orderSn;

        @ExcelProperty(value = "申请时间")
        @Schema(description =  "申请时间")
        private Date createTime;

        @ExcelProperty(value = "会员用户名")
        @Schema(description =  "会员用户名")
        private String memberUsername;

        @ExcelProperty(value = "退款金额")
        @Schema(description =  "退款金额")
        private BigDecimal returnAmount;

        @ExcelProperty(value = "退货人姓名")
        @Schema(description =  "退货人姓名")
        private String returnName;

        @ExcelProperty(value = "退货人电话")
        @Schema(description =  "退货人电话")
        private String returnPhone;

        @ExcelProperty(value = "申请状态[0-&gt;待处理；1-&gt;退货中；2-&gt;已完成；3-&gt;已拒绝]")
        @Schema(description =  "申请状态[0-&gt;待处理；1-&gt;退货中；2-&gt;已完成；3-&gt;已拒绝]")
        private Integer status;

        @ExcelProperty(value = "处理时间")
        @Schema(description =  "处理时间")
        private Date handleTime;

        @ExcelProperty(value = "商品图片")
        @Schema(description =  "商品图片")
        private String skuImg;

        @ExcelProperty(value = "商品名称")
        @Schema(description =  "商品名称")
        private String skuName;

        @ExcelProperty(value = "商品品牌")
        @Schema(description =  "商品品牌")
        private String skuBrand;

        @ExcelProperty(value = "商品销售属性(JSON)")
        @Schema(description =  "商品销售属性(JSON)")
        private String skuAttrsVals;

        @ExcelProperty(value = "退货数量")
        @Schema(description =  "退货数量")
        private Long skuCount;

        @ExcelProperty(value = "商品单价")
        @Schema(description =  "商品单价")
        private BigDecimal skuPrice;

        @ExcelProperty(value = "商品实际支付单价")
        @Schema(description =  "商品实际支付单价")
        private BigDecimal skuRealPrice;

        @ExcelProperty(value = "原因")
        @Schema(description =  "原因")
        private String reason;

        @ExcelProperty(value = "描述")
        @Schema(description =  "描述")
        private String description述;

        @ExcelProperty(value = "凭证图片，以逗号隔开")
        @Schema(description =  "凭证图片，以逗号隔开")
        private String descPics;

        @ExcelProperty(value = "处理备注")
        @Schema(description =  "处理备注")
        private String handleNote;

        @ExcelProperty(value = "处理人员")
        @Schema(description =  "处理人员")
        private String handleMan;

        @ExcelProperty(value = "收货人")
        @Schema(description =  "收货人")
        private String receiveMan;

        @ExcelProperty(value = "收货时间")
        @Schema(description =  "收货时间")
        private Date receiveTime;

        @ExcelProperty(value = "收货备注")
        @Schema(description =  "收货备注")
        private String receiveNote;

        @ExcelProperty(value = "收货电话")
        @Schema(description =  "收货电话")
        private String receivePhone;

        @ExcelProperty(value = "公司收货地址")
        @Schema(description =  "公司收货地址")
        private String companyAddress;


}
