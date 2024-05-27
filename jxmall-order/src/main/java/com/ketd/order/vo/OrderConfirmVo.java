package com.ketd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-27  21:56
 */
@Data
public class OrderConfirmVo implements Serializable {

    private List<MemberAddressVo>  addresses;

    private List<OrderItemVo> items;

    private Integer integration;

    private BigDecimal total;

    private BigDecimal payPrice;
}
