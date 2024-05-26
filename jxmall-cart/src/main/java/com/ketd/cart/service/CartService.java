package com.ketd.cart.service;

import com.ketd.cart.vo.Check;
import com.ketd.common.result.Result;

import java.util.List;

public interface CartService {
    Result<?> add(Long skuId, Long num);

    Result<?> get();

    Result<?> update(Long skuId, Long num);

    Result<?> delete(Long[] skuIds);

    Result<?> updateCheck(List<Check> checks);
}
