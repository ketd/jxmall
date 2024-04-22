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
 * 商品属性对象 pms_attr
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_attr")
@Data
public class Attr  implements Serializable
{


    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "属性id")
        @Schema(description =  "属性id")
        private Long attrId;

        @ExcelProperty(value = "属性名")
        @Schema(description =  "属性名")
        private String attrName;

        @ExcelProperty(value = "是否需要检索[0-不需要，1-需要]")
        @Schema(description =  "是否需要检索[0-不需要，1-需要]")
        private Long searchType;

        @ExcelProperty(value = "值类型[0-为单个值，1-可以选择多个值]")
        @Schema(description =  "值类型[0-为单个值，1-可以选择多个值]")
        private Long valueType;

        @ExcelProperty(value = "属性图标")
        @Schema(description =  "属性图标")
        private String icon;

        @ExcelProperty(value = "可选值列表[用逗号分隔]")
        @Schema(description =  "可选值列表[用逗号分隔]")
        private String valueSelect;

        @ExcelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
        @Schema(description =  "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
        private Long attrType;

        @ExcelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
        @Schema(description =  "启用状态[0 - 禁用，1 - 启用]")
        private Long enable;

        @ExcelProperty(value = "所属分类")
        @Schema(description =  "所属分类")
        private Long catelogId;


        @ExcelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
        @Schema(description =  "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
        private Long showDesc;


}
