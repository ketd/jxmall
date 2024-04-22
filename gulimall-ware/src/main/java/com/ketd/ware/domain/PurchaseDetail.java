package com.ketd.ware.domain;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 采购详情对象 wms_purchase_detail
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_purchase_detail")
@Data
public class PurchaseDetail  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "购买详情id")
        @Schema(description =  "购买详情id")
        private Long id;

        @ExcelProperty(value = "采购单id")
        @Schema(description =  "采购单id")
        private Long purchaseId;

        @ExcelProperty(value = "采购商品id")
        @Schema(description =  "采购商品id")
        private Long skuId;

        @ExcelProperty(value = "采购数量")
        @Schema(description =  "采购数量")
        private Long skuNum;

        @ExcelProperty(value = "采购金额")
        @Schema(description =  "采购金额")
        private BigDecimal skuPrice;

        @ExcelProperty(value = "仓库id")
        @Schema(description =  "仓库id")
        private Long wareId;

        @ExcelProperty(value = "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
        @Schema(description =  "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
        private Long status;


}
