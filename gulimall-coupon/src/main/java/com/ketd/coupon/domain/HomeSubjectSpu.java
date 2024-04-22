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
 * 专题商品对象 sms_home_subject_spu
 *
 * @author ketd
 * @date 2024-04-20
 */

@TableName(value ="sms_home_subject_spu")
@Data
public class HomeSubjectSpu  implements Serializable
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

        @ExcelProperty(value = "专题id")
        @Schema(description =  "专题id")
        private Long subjectId;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;


}
