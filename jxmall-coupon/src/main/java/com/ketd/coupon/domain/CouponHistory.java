package com.ketd.coupon.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 优惠券领取历史记录对象 sms_coupon_history
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_coupon_history")
@Data
public class CouponHistory  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "优惠券id")
        @Schema(description =  "优惠券id")
        private Long couponId;

        @ExcelProperty(value = "会员id")
        @Schema(description =  "会员id")
        private Long memberId;

        @ExcelProperty(value = "会员名字")
        @Schema(description =  "会员名字")
        private String memberNickName;

        @ExcelProperty(value = "获取方式[0-&gt;后台赠送；1-&gt;主动领取]")
        @Schema(description =  "获取方式[0-&gt;后台赠送；1-&gt;主动领取]")
        private Integer getType;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;

        @ExcelProperty(value = "使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]")
        @Schema(description =  "使用状态[0-&gt;未使用；1-&gt;已使用；2-&gt;已过期]")
        private Integer useStateType;

        @ExcelProperty(value = "使用时间")
        @Schema(description =  "使用时间")
        private Date useTime;

        @ExcelProperty(value = "订单id")
        @Schema(description =  "订单id")
        private Long orderId;

        @ExcelProperty(value = "订单号")
        @Schema(description =  "订单号")
        private Long orderSn;


}
