package com.ketd.product.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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
}