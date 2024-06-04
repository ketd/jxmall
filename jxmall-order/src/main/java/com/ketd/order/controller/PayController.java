package com.ketd.order.controller;

import com.ketd.common.result.Result;
import com.ketd.order.service.PayService;
import com.ketd.order.service.impl.PayServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.controller
 * @Author: ketd
 * @CreateTime: 2024-06-04  17:37
 */

@Tag(name = "支付Controller")
@RestController
@RequestMapping("/order/Pay")
public class PayController {

    @Autowired
    private PayService  payService;

    @PutMapping("/BalancePay")
    private Result<?> BalancePay(@RequestParam(value = "orderId") Long orderId){

        return  payService.BalancePay(orderId);
    }
}
