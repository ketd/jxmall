package com.ketd.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description =  "会员积分")
    private Long integration;

    @Schema(description ="防止重复提交令牌")
    private String orderToken;

    @Schema(description = "总支付金额")
    private BigDecimal totalPayAmount;

    @Schema(description = "实际支付金额")
    private BigDecimal payPrice;
}
