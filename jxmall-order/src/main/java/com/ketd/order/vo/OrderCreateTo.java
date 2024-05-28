package com.ketd.order.vo;

import com.ketd.order.domain.Order;
import com.ketd.order.domain.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-28  18:07
 */
@Data
public class OrderCreateTo {

    @Schema(description =  "订单")
    private Order order;

    @Schema(description =  "订单项")
    private List<OrderItem> orderItemList;

    @Schema(description =  "支付金额")
    private BigDecimal payAmount;

    @Schema(description =  "运费")
    private BigDecimal fare;
}
