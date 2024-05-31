package com.ketd.common.api.cart;

import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-cart-service")
public interface CartOpenFeignApi {

    @DeleteMapping("/cart/cart/delete")
    public Result<?> delete(@RequestBody Long[] skuIds);
}
