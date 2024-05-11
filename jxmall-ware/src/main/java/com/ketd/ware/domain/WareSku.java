package com.ketd.ware.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品库存对象 wms_ware_sku
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_ware_sku")
@Data
public class WareSku  implements Serializable
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

        @ExcelProperty(value = "仓库id")
        @Schema(description =  "仓库id")
        private Long wareId;

        @ExcelProperty(value = "库存数")
        @Schema(description =  "库存数")
        private Long stock;

        @ExcelProperty(value = "sku_name")
        @Schema(description =  "sku_name")
        private String skuName;

        @ExcelProperty(value = "锁定库存")
        @Schema(description =  "锁定库存")
        private Long stockLocked;


}
