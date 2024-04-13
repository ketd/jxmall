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
 * sku销售属性&值对象 pms_sku_sale_attr_value
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_sku_sale_attr_value")
@Data
public class SkuSaleAttrValue  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "sku_id")
        @Schema(description =  "sku_id")
        private Long skuId;

        @ExcelProperty(value = "attr_id")
        @Schema(description =  "attr_id")
        private Long attrId;

        @ExcelProperty(value = "销售属性名")
        @Schema(description =  "销售属性名")
        private String attrName;

        @ExcelProperty(value = "销售属性值")
        @Schema(description =  "销售属性值")
        private String attrValue;

        @ExcelProperty(value = "顺序")
        @Schema(description =  "顺序")
        private Long attrSort;


}
