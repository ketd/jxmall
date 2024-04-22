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
 * 库存工作单对象 wms_ware_order_task_detail
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_ware_order_task_detail")
@Data
public class WareOrderTaskDetail  implements Serializable
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

        @ExcelProperty(value = "sku_name")
        @Schema(description =  "sku_name")
        private String skuName;

        @ExcelProperty(value = "购买个数")
        @Schema(description =  "购买个数")
        private Long skuNum;

        @ExcelProperty(value = "工作单id")
        @Schema(description =  "工作单id")
        private Long taskId;


}
