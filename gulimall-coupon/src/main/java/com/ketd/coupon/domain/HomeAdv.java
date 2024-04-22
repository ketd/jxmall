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
 * 首页轮播广告对象 sms_home_adv
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_home_adv")
@Data
public class HomeAdv  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "名字")
        @Schema(description =  "名字")
        private String name;

        @ExcelProperty(value = "图片地址")
        @Schema(description =  "图片地址")
        private String pic;

        @ExcelProperty(value = "开始时间")
        @Schema(description =  "开始时间")
        private Date startTime;

        @ExcelProperty(value = "结束时间")
        @Schema(description =  "结束时间")
        private Date endTime;

        @ExcelProperty(value = "状态")
        @Schema(description =  "状态")
        private Integer status;

        @ExcelProperty(value = "点击数")
        @Schema(description =  "点击数")
        private Long clickCount;

        @ExcelProperty(value = "广告详情连接地址")
        @Schema(description =  "广告详情连接地址")
        private String url;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;

        @ExcelProperty(value = "发布者")
        @Schema(description =  "发布者")
        private Long publisherId;

        @ExcelProperty(value = "审核者")
        @Schema(description =  "审核者")
        private Long authId;


}
