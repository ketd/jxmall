package com.ketd.coupon.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 优惠券信息对象 sms_coupon
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_coupon")
@Data
public class Coupon  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]")
        @Schema(description =  "优惠卷类型[0-&gt;全场赠券；1-&gt;会员赠券；2-&gt;购物赠券；3-&gt;注册赠券]")
        private Integer couponType;

        @ExcelProperty(value = "优惠券图片")
        @Schema(description =  "优惠券图片")
        private String couponImg;

        @ExcelProperty(value = "优惠卷名字")
        @Schema(description =  "优惠卷名字")
        private String couponName;

        @ExcelProperty(value = "数量")
        @Schema(description =  "数量")
        private Long num;

        @ExcelProperty(value = "金额")
        @Schema(description =  "金额")
        private BigDecimal amount;

        @ExcelProperty(value = "每人限领张数")
        @Schema(description =  "每人限领张数")
        private Long perLimit;

        @ExcelProperty(value = "使用门槛")
        @Schema(description =  "使用门槛")
        private BigDecimal minPoint;

        @ExcelProperty(value = "开始时间")
        @Schema(description =  "开始时间")
        private Date startTime;

        @ExcelProperty(value = "结束时间")
        @Schema(description =  "结束时间")
        private Date endTime;

        @ExcelProperty(value = "使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]")
        @Schema(description =  "使用类型[0-&gt;全场通用；1-&gt;指定分类；2-&gt;指定商品]")
        private Integer useType;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;

        @ExcelProperty(value = "发行数量")
        @Schema(description =  "发行数量")
        private Long publishCount;

        @ExcelProperty(value = "已使用数量")
        @Schema(description =  "已使用数量")
        private Long useCount;

        @ExcelProperty(value = "领取数量")
        @Schema(description =  "领取数量")
        private Long receiveCount;

        @ExcelProperty(value = "可以领取的开始日期")
        @Schema(description =  "可以领取的开始日期")
        private Date enableStartTime;

        @ExcelProperty(value = "可以领取的结束日期")
        @Schema(description =  "可以领取的结束日期")
        private Date enableEndTime;

        @ExcelProperty(value = "优惠码")
        @Schema(description =  "优惠码")
        private String code;

        @ExcelProperty(value = "可以领取的会员等级[0-&gt;不限等级，其他-对应等级]")
        @Schema(description =  "可以领取的会员等级[0-&gt;不限等级，其他-对应等级]")
        private Integer memberLevel;

        @ExcelProperty(value = "发布状态[0-未发布，1-已发布]")
        @Schema(description =  "发布状态[0-未发布，1-已发布]")
        private Integer publish;


}
