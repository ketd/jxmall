package com.ketd.ware.domain;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 采购信息对象 wms_purchase
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_purchase")
@Data
public class Purchase  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "采购单id")
        @Schema(description =  "采购单id")
        private Long id;

        @ExcelProperty(value = "采购人id")
        @Schema(description =  "采购人id")
        private Long assigneeId;

        @ExcelProperty(value = "采购人名")
        @Schema(description =  "采购人名")
        private String assigneeName;

        @ExcelProperty(value = "联系方式")
        @Schema(description =  "联系方式")
        private String phone;

        @ExcelProperty(value = "优先级")
        @Schema(description =  "优先级")
        private Long priority;

        @ExcelProperty(value = "状态")
        @Schema(description =  "状态")
        private Long status;

        @ExcelProperty(value = "仓库id")
        @Schema(description =  "仓库id")
        private Long wareId;

        @ExcelProperty(value = "总金额")
        @Schema(description =  "总金额")
        private BigDecimal amount;

        @ExcelProperty(value = "创建日期")
        @Schema(description =  "创建日期")
        private Date createTime;

        @ExcelProperty(value = "更新日期")
        @Schema(description =  "更新日期")
        private Date updateTime;


}
