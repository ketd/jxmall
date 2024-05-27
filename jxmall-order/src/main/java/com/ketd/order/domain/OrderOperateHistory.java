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
 * 订单操作历史记录对象 oms_order_operate_history
 *
 * @author ketd
 * @date 2024-05-27
 */

@TableName(value ="oms_order_operate_history")
@Data
public class OrderOperateHistory  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "订单id")
        @Schema(description =  "订单id")
        private Long orderId;

        @ExcelProperty(value = "操作人[用户；系统；后台管理员]")
        @Schema(description =  "操作人[用户；系统；后台管理员]")
        private String operateMan;

        @ExcelProperty(value = "操作时间")
        @Schema(description =  "操作时间")
        private Date createTime;

        @ExcelProperty(value = "订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】")
        @Schema(description =  "订单状态【0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;无效订单】")
        private Long orderStatus;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;


}
