package com.ketd.cart.controller;

import com.ketd.cart.service.CartService;
import com.ketd.cart.vo.Check;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    public Result<?> update(@RequestParam("skuId") Long skuId, @RequestParam("num") Long num){
        return cartService.update(skuId,num);
    }
    @PutMapping("/check")
    public Result<?> updateCheck(@RequestBody List<Check> checks){
        return cartService.updateCheck(checks);
    }
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody Long[] skuIds){
        return cartService.delete(skuIds);
    }
}
