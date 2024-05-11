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
 * 秒杀活动对象 sms_seckill_promotion
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_seckill_promotion")
@Data
public class SeckillPromotion  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "活动标题")
        @Schema(description =  "活动标题")
        private String title;

        @ExcelProperty(value = "开始日期")
        @Schema(description =  "开始日期")
        private Date startTime;

        @ExcelProperty(value = "结束日期")
        @Schema(description =  "结束日期")
        private Date endTime;

        @ExcelProperty(value = "上下线状态")
        @Schema(description =  "上下线状态")
        private Long status;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;

        @ExcelProperty(value = "创建人")
        @Schema(description =  "创建人")
        private Long userId;


}
