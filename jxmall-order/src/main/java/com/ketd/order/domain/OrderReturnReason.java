package com.ketd.order.domain;

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
 * 退货原因对象 oms_order_return_reason
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_order_return_reason")
@Data
public class OrderReturnReason  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "退货原因名")
        @Schema(description =  "退货原因名")
        private String name;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;

        @ExcelProperty(value = "启用状态")
        @Schema(description =  "启用状态")
        private Integer status;

        @ExcelProperty(value = "create_time")
        @Schema(description =  "create_time")
        private Date createTime;


}
