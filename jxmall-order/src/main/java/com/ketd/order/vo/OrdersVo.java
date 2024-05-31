package com.ketd.order.vo;


import com.ketd.order.domain.Order;
import com.ketd.order.domain.OrderItem;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-30  14:08
 */
@Data
public class OrdersVo {
    private Order order;
    private List<OrderItem> orderItem;
}
