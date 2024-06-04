package com.ketd.order.service;

import com.ketd.common.result.Result;

public interface PayService {
    Result<?> BalancePay(Long orderId);
}
