package com.ketd.product.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponHistoryOpenFeignApiTest {

    @Autowired
    private CouponHistoryOpenFeignApi couponHistoryOpenFeignApi;
    @Test
    void getInfo() {
        couponHistoryOpenFeignApi.getInfo(1L);
    }
}