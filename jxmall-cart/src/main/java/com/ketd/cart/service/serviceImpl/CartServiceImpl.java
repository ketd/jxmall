package com.ketd.cart.service.serviceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketd.cart.Interceptors.LoginProtectedInterceptor;
import com.ketd.cart.service.CartService;
import com.ketd.cart.util.RedisUtil;
import com.ketd.cart.vo.CartItem;
import com.ketd.cart.vo.Check;
import com.ketd.common.api.product.BrandOpenFeignApi;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.domain.product.BrandTO;
import com.ketd.common.domain.product.SkuInfoTO;
import com.ketd.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.cart.service.serviceImpl
 * @Author: ketd
 * @CreateTime: 2024-05-18  16:59
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SkuInfoOpenFeignApi skuInfoOpenFeignApi;

    @Autowired
    private BrandOpenFeignApi  brandOpenFeignApi;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private ObjectMapper objectMapper;



    /*TODO
    *  待解决问题：
    * 1.相同skuId，count需要+=num*/
    @Override
    public Result<?> add(Long skuId, Long num) {
        BoundHashOperations<String, Object, Object> boundGeoOperations = getCartItem();

        // 获取已有的 cartItem
        String existingCartItemJson = (String) boundGeoOperations.get(skuId.toString());
        CartItem cartItem;
        if (existingCartItemJson != null) {
            try {
                cartItem = objectMapper.readValue(existingCartItemJson, CartItem.class);
                // 更新数量
                cartItem.setCount(cartItem.getCount() + num);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse existing cart item JSON", e);
            }
        } else {
            // 不存在则创建新的 cartItem
            cartItem = new CartItem();
            SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(skuId).getData();
            BrandTO brandTO = brandOpenFeignApi.getInfo(skuInfoTO.getBrandId()).getData();

            cartItem.setSkuId(skuId);
            cartItem.setSkuTitle(skuInfoTO.getSkuTitle());
            cartItem.setImage(skuInfoTO.getSkuDefaultImg());
            cartItem.setPrice(skuInfoTO.getPrice());
            cartItem.setCount(num);
            cartItem.setCheck(false);
            cartItem.setBrandName(brandTO.getName());
            cartItem.setSkuAttr(skuInfoTO.getSkuAttrValues());
        }

        try {
            String jsonString = objectMapper.writeValueAsString(cartItem);
            boundGeoOperations.put(skuId.toString(), jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize cart item to JSON", e);
        }

        return Result.ok(cartItem);
    }


    @Override
    public Result<?> get() {
        BoundHashOperations<String, Object,Object> boundGeoOperations=  getCartItem();

        List<CartItem> cartItems = new ArrayList<>();

        List<Object> values = boundGeoOperations.values();
        if (values != null) {
            for (Object value : values) {
                try {
                    CartItem cartItem = objectMapper.readValue(value.toString(), CartItem.class);
                    cartItems.add(cartItem);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return Result.ok(cartItems);

    }

    @Override
    public Result<?> update(Long skuId, Long num) {
        BoundHashOperations<String, Object, Object> boundGeoOperations = getCartItem();

        // 获取已有的 cartItem
        String existingCartItemJson = (String) boundGeoOperations.get(skuId.toString());
        if (existingCartItemJson != null) {
            try {
                // 解析已有的 cartItem
                CartItem cartItem = objectMapper.readValue(existingCartItemJson, CartItem.class);

                // 更新数量
                cartItem.setCount(num);

                // 将更新后的 cartItem 写回缓存
                String jsonString = objectMapper.writeValueAsString(cartItem);
                boundGeoOperations.put(skuId.toString(), jsonString);

                return Result.ok(cartItem);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse or serialize cart item JSON", e);
            }
        } else {
            // 如果购物车中不存在该 skuId 的项，返回错误信息或其他处理逻辑
            return Result.error("Item not found in cart");
        }
    }

    @Override
    public Result<?> delete(Long[] skuIds) {
        BoundHashOperations<String, Object, Object> boundGeoOperations = getCartItem();

        // 将 Long[] 转换为 String[]
        String[] skuIdStrings = Arrays.stream(skuIds)
                .map(String::valueOf)
                .toArray(String[]::new);

        // 一次性删除所有指定的 skuId 项
        boundGeoOperations.delete((Object[]) skuIdStrings);

        return Result.ok(null);
    }


    @Override
    public Result<?> updateCheck(List<Check> checks) {
        BoundHashOperations<String, Object, Object> boundGeoOperations = getCartItem();

        checks.forEach(check -> {
            String existingCartItemJson = (String) boundGeoOperations.get(check.getSkuId().toString());
            if (existingCartItemJson != null) {
                try {
                    CartItem cartItem = objectMapper.readValue(existingCartItemJson, CartItem.class);
                    cartItem.setCheck(check.getCheck());
                    String jsonString = objectMapper.writeValueAsString(cartItem);
                    boundGeoOperations.put(check.getSkuId().toString(), jsonString);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to parse or serialize cart item JSON", e);
                }
            }
        });

        return Result.ok(null);
    }


    private BoundHashOperations<String, Object, Object> getCartItem() {
        MemberTO memberTO = LoginProtectedInterceptor.threadLocal.get();
        String key = "jxmall-cart:" + memberTO.getId();

        // 检查 boundGeoOperations 是否为 null
        return stringRedisTemplate.boundHashOps(key);

    }

}
