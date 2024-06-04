package com.ketd.order.service.impl;

import com.ketd.common.domain.member.BalanceDetailsTO;
import com.ketd.common.domain.member.BalanceTO;
import com.ketd.common.enume.OrderStatusEnum;
import com.ketd.common.no_authentication_api.member.NoAuthenticationBalanceDetailsOpenFeignApi;
import com.ketd.common.no_authentication_api.member.NoAuthenticationBalanceOpenFeignApi;
import com.ketd.common.result.Result;
import com.ketd.order.domain.Order;
import com.ketd.order.mapper.OrderMapper;
import com.ketd.order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.service.impl
 * @Author: ketd
 * @CreateTime: 2024-06-04  17:40
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderMapper  orderMapper;

    @Autowired
    private NoAuthenticationBalanceOpenFeignApi noAuthenticationBalanceOpenFeignApi;

    @Autowired
    private NoAuthenticationBalanceDetailsOpenFeignApi noAuthenticationBalanceDetailsOpenFeignApi;

    @Override
    public Result<?> BalancePay(Long orderId) {

        Order order = orderMapper.selectById(orderId);
        BalanceTO  balanceTO = noAuthenticationBalanceOpenFeignApi.getInfoByMemberId(order.getMemberId()).getData();
        BigDecimal balanceBefore=balanceTO.getBalance();
        BigDecimal balanceAfter=balanceBefore.subtract(order.getTotalAmount());
        if(balanceAfter.compareTo(BigDecimal.ZERO)>= 0){
            balanceTO.setBalance(balanceAfter);

            noAuthenticationBalanceOpenFeignApi.edit(balanceTO);

            BalanceDetailsTO  balanceDetailsTO = new BalanceDetailsTO();
            balanceDetailsTO.setOrderId(orderId);
            balanceDetailsTO.setOrderSn(order.getOrderSn());
            balanceDetailsTO.setMemberId(order.getMemberId());
            balanceDetailsTO.setTransactionType(1);
            balanceDetailsTO.setTransactionDate(new   Date());
            balanceDetailsTO.setTransactionAmount(order.getTotalAmount());
            balanceDetailsTO.setNote(order.getNote());
            balanceDetailsTO.setBalanceBefore(balanceBefore);
            balanceDetailsTO.setBalanceAfter(balanceTO.getBalance());
            noAuthenticationBalanceDetailsOpenFeignApi.add(balanceDetailsTO);

            order.setStatus(OrderStatusEnum.PAYED.getCode());
            orderMapper.updateById(order);
            return Result.ok(null);
        }else{
            return Result.error("余额不足");
        }
    }
}
