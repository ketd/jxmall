package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * spu信息对象 pms_spu_info
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_spu_info")
@Data
public class SpuInfo  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "商品id")
        @Schema(description =  "商品id")
        private Long id;

        @ExcelProperty(value = "商品名称")
        @Schema(description =  "商品名称")
        private String spuName;

        @ExcelProperty(value = "商品描述")
        @Schema(description =  "商品描述")
        private String spuDescription;

        @ExcelProperty(value = "所属分类id")
        @Schema(description =  "所属分类id")
        private Long catalogId;

        @ExcelProperty(value = "品牌id")
        @Schema(description =  "品牌id")
        private Long brandId;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private BigDecimal weight;

        @ExcelProperty(value = "上架状态[0 - 下架，1 - 上架]")
        @Schema(description =  "上架状态[0 - 下架，1 - 上架]")
        private Long publishStatus;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private Date createTime;

        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private Date updateTime;


}
