package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        @NotBlank(message = "品牌名不能为空")//不能是空字符串
        @Pattern(regexp = "^[\\u4e00-\\u9fa5\\w]{2,50}$",message = "品牌名只能是中文、字母、数字，长度2-50")//只能是中文、字母、数字，长度2-50
        private String name;

        @ExcelProperty(value = "品牌logo地址")
        @Schema(description =  "品牌logo地址")
        @NotBlank(message = "品牌logo地址不能为空")
        private String logo;

        @ExcelProperty(value = "介绍")
        @Schema(description =  "介绍")
        private String descript;

        @ExcelProperty(value = "显示状态[0-不显示；1-显示]")
        @Schema(description =  "显示状态[0-不显示；1-显示]")
        @Pattern(regexp = "^[0-1]$",message = "显示状态只能是0或1")
        private Long showStatus;

        @ExcelProperty(value = "检索首字母")
        @Schema(description =  "检索首字母")
        @Pattern(regexp = "^[a-zA-Z]$",message = "首字母只能是一个字母")
        private String firstLetter;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        @Min(value = 0,message = "排序必须大于等于0")
        private Long sort;


}
