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
 * 秒杀活动场次对象 sms_seckill_session
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_seckill_session")
@Data
public class SeckillSession  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "场次名称")
        @Schema(description =  "场次名称")
        private String name;

        @ExcelProperty(value = "每日开始时间")
        @Schema(description =  "每日开始时间")
        private Date startTime;

        @ExcelProperty(value = "每日结束时间")
        @Schema(description =  "每日结束时间")
        private Date endTime;

        @ExcelProperty(value = "启用状态")
        @Schema(description =  "启用状态")
        private Integer status;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;


}
