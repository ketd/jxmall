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
 * sku图片对象 pms_sku_images
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_sku_images")
@Data
public class SkuImages  implements Serializable
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

        @ExcelProperty(value = "图片地址")
        @Schema(description =  "图片地址")
        private String imgUrl;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long imgSort;

        @ExcelProperty(value = "默认图[0 - 不是默认图，1 - 是默认图]")
        @Schema(description =  "默认图[0 - 不是默认图，1 - 是默认图]")
        private Long defaultImg;


}
