package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * sku信息对象 pms_sku_info
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_sku_info")
@Data
public class SkuInfo  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "skuId")
        @Schema(description =  "skuId")
        private Long skuId;

        @ExcelProperty(value = "spuId")
        @Schema(description =  "spuId")
        private Long spuId;

        @ExcelProperty(value = "sku名称")
        @Schema(description =  "sku名称")
        private String skuName;

        @ExcelProperty(value = "sku介绍描述")
        @Schema(description =  "sku介绍描述")
        private String skuDesc;

        @ExcelProperty(value = "所属分类id")
        @Schema(description =  "所属分类id")
        private Long catalogId;

        @ExcelProperty(value = "品牌id")
        @Schema(description =  "品牌id")
        private Long brandId;

        @ExcelProperty(value = "默认图片")
        @Schema(description =  "默认图片")
        private String skuDefaultImg;

        @ExcelProperty(value = "标题")
        @Schema(description =  "标题")
        private String skuTitle;

        @ExcelProperty(value = "副标题")
        @Schema(description =  "副标题")
        private String skuSubtitle;

        @ExcelProperty(value = "价格")
        @Schema(description =  "价格")
        private BigDecimal price;

        @ExcelProperty(value = "销量")
        @Schema(description =  "销量")
        private Long saleCount;


}
