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
 * 品牌分类关联对象 pms_category_brand_relation
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_category_brand_relation")
@Data
public class CategoryBrandRelation  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private Long id;

        @ExcelProperty(value = "品牌id")
        @Schema(description =  "品牌id")
        private Long brandId;

        @ExcelProperty(value = "分类id")
        @Schema(description =  "分类id")
        private Long catelogId;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private String brandName;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private String catelogName;


}
