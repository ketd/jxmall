package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品三级分类对象 pms_category
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_category")
@Data
public class Category  implements Serializable
{
        @Serial
        private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "分类id")
        @Schema(description =  "分类id")
        private Long catId;

        @ExcelProperty(value = "分类名称")
        @Schema(description =  "分类名称")
        private String name;

        @ExcelProperty(value = "父分类id")
        @Schema(description =  "父分类id")
        private Long parentCid;

        @ExcelProperty(value = "层级")
        @Schema(description =  "层级")
        private Long catLevel;

        @ExcelProperty(value = "是否显示[0-不显示，1显示]")
        @Schema(description =  "是否显示[0-不显示，1显示]")
        private Long showStatus;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;

        @ExcelProperty(value = "图标地址")
        @Schema(description =  "图标地址")
        private String icon;

        @ExcelProperty(value = "计量单位")
        @Schema(description =  "计量单位")
        private String productUnit;

        @ExcelProperty(value = "商品数量")
        @Schema(description =  "商品数量")
        private Long productCount;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @TableField(exist = false)
        private List<Category> children;

}
