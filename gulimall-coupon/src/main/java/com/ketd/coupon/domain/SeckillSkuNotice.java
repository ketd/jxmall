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
 * 秒杀商品通知订阅对象 sms_seckill_sku_notice
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_seckill_sku_notice")
@Data
public class SeckillSkuNotice  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "member_id")
        @Schema(description =  "member_id")
        private Long memberId;

        @ExcelProperty(value = "sku_id")
        @Schema(description =  "sku_id")
        private Long skuId;

        @ExcelProperty(value = "活动场次id")
        @Schema(description =  "活动场次id")
        private Long sessionId;

        @ExcelProperty(value = "订阅时间")
        @Schema(description =  "订阅时间")
        private Date subcribeTime;

        @ExcelProperty(value = "发送时间")
        @Schema(description =  "发送时间")
        private Date sendTime;

        @ExcelProperty(value = "通知方式[0-短信，1-邮件]")
        @Schema(description =  "通知方式[0-短信，1-邮件]")
        private Integer noticeType;


}
