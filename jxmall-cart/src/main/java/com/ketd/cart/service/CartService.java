package com.ketd.cart.service;

import com.ketd.common.result.Result;

public interface CartService {
    Result<?> add(Long skuId, Long num);

    Result<?> get();
}
