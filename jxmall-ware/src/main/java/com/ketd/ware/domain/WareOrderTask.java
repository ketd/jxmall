package com.ketd.ware.domain;

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
 * 库存工作单对象 wms_ware_order_task
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_ware_order_task")
@Data
public class WareOrderTask  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "order_id")
        @Schema(description =  "order_id")
        private Long orderId;

        @ExcelProperty(value = "order_sn")
        @Schema(description =  "order_sn")
        private String orderSn;

        @ExcelProperty(value = "收货人")
        @Schema(description =  "收货人")
        private String consignee;

        @ExcelProperty(value = "收货人电话")
        @Schema(description =  "收货人电话")
        private String consigneeTel;

        @ExcelProperty(value = "配送地址")
        @Schema(description =  "配送地址")
        private String deliveryAddress;

        @ExcelProperty(value = "订单备注")
        @Schema(description =  "订单备注")
        private String orderComment;

        @ExcelProperty(value = "付款方式【 1:在线付款 2:货到付款】")
        @Schema(description =  "付款方式【 1:在线付款 2:货到付款】")
        private Integer paymentWay;

        @ExcelProperty(value = "任务状态")
        @Schema(description =  "任务状态")
        private Long taskStatus;

        @ExcelProperty(value = "订单描述")
        @Schema(description =  "订单描述")
        private String orderBody;

        @ExcelProperty(value = "物流单号")
        @Schema(description =  "物流单号")
        private String trackingNo;

        @ExcelProperty(value = "create_time")
        @Schema(description =  "create_time")
        private Date createTime;

        @ExcelProperty(value = "仓库id")
        @Schema(description =  "仓库id")
        private Long wareId;

        @ExcelProperty(value = "工作单备注")
        @Schema(description =  "工作单备注")
        private String taskComment;


}
