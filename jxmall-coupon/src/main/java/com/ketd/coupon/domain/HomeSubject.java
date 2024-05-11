package com.ketd.coupon.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】对象 sms_home_subject
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_home_subject")
@Data
public class HomeSubject  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "专题名字")
        @Schema(description =  "专题名字")
        private String name;

        @ExcelProperty(value = "专题标题")
        @Schema(description =  "专题标题")
        private String title;

        @ExcelProperty(value = "专题副标题")
        @Schema(description =  "专题副标题")
        private String subTitle;

        @ExcelProperty(value = "显示状态")
        @Schema(description =  "显示状态")
        private Integer status;

        @ExcelProperty(value = "详情连接")
        @Schema(description =  "详情连接")
        private String url;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;

        @ExcelProperty(value = "专题图片地址")
        @Schema(description =  "专题图片地址")
        private String img;


}
