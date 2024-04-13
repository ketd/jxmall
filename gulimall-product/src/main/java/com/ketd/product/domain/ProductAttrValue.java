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
 * spu属性值对象 pms_product_attr_value
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_product_attr_value")
@Data
public class ProductAttrValue  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "商品id")
        @Schema(description =  "商品id")
        private Long spuId;

        @ExcelProperty(value = "属性id")
        @Schema(description =  "属性id")
        private Long attrId;

        @ExcelProperty(value = "属性名")
        @Schema(description =  "属性名")
        private String attrName;

        @ExcelProperty(value = "属性值")
        @Schema(description =  "属性值")
        private String attrValue;

        @ExcelProperty(value = "顺序")
        @Schema(description =  "顺序")
        private Long attrSort;

        @ExcelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
        @Schema(description =  "快速展示【是否展示在介绍上；0-否 1-是】")
        private Long quickShow;


}
