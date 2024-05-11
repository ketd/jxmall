package com.ketd.product.service.impl;




import com.ketd.common.api.coupon.CouponHistoryOpenFeignApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;




@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    void findCategoryPath() {
        Long categoryId = 225L;
        Long[] categoryPath = categoryService.findCategoryPath(categoryId);
        System.out.println(Arrays.toString(categoryPath));
    }

    @Autowired
    private CouponHistoryOpenFeignApi couponHistoryOpenFeignApi;


    @Test
    void openFeignTest() {
        couponHistoryOpenFeignApi.getInfo(1L);
    }
}