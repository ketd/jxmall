package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 品牌对象 pms_brand
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_brand")
@Data
public class Brand  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "品牌id")
        @Schema(description =  "品牌id")
        private Long brandId;

        @ExcelProperty(value = "品牌名")
        @Schema(description =  "品牌名")
        private String name;

        @ExcelProperty(value = "品牌logo地址")
        @Schema(description =  "品牌logo地址")
        private String logo;

        @ExcelProperty(value = "介绍")
        @Schema(description =  "介绍")
        private String descript;

        @ExcelProperty(value = "显示状态[0-不显示；1-显示]")
        @Schema(description =  "显示状态[0-不显示；1-显示]")
        private Long showStatus;

        @ExcelProperty(value = "检索首字母")
        @Schema(description =  "检索首字母")
        private String firstLetter;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;


}
