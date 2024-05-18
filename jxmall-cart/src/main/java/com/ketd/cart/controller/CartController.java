package com.ketd.cart.controller;

import com.ketd.cart.service.CartService;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.cart.controller
 * @Author: ketd
 * @CreateTime: 2024-05-18  16:54
 */
@RestController
@RequestMapping("/cart/cart")
public class CartController {

    @Autowired
    private CartService  cartService;

    @PostMapping("/add")
    public Result<?> add(@RequestParam("skuId") Long skuId, @RequestParam("num") Long num){
        return cartService.add(skuId,num);
    }
    @GetMapping("/get")
    public Result<?> get(){
        return cartService.get();
    }
}
