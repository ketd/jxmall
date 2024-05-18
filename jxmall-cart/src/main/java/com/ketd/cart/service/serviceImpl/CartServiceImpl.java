package com.ketd.cart.service.serviceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketd.cart.Interceptors.LoginProtectedInterceptor;
import com.ketd.cart.service.CartService;
import com.ketd.cart.util.RedisUtil;
import com.ketd.cart.vo.CartItem;
import com.ketd.common.api.product.BrandOpenFeignApi;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.domain.product.BrandTO;
import com.ketd.common.domain.product.SkuInfoTO;
import com.ketd.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
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



    @Override
    public Result<?> add(Long skuId, Long num) {
        CartItem cartItem = new CartItem();
        SkuInfoTO skuInfoTO = skuInfoOpenFeignApi.getInfo(skuId).getData();
        BrandTO  brandTO = brandOpenFeignApi.getInfo(skuInfoTO.getBrandId()).getData();
        BoundHashOperations<String, Object,Object> boundGeoOperations=  getCartItem();
        cartItem.setSkuId(skuId);
        cartItem.setSkuTitle(skuInfoTO.getSkuTitle());
        cartItem.setImage(skuInfoTO.getSkuDefaultImg());
        cartItem.setPrice(skuInfoTO.getPrice());
        cartItem.setCount(num);
        cartItem.setBrandName(brandTO.getName());
        cartItem.setSkuAttr(skuInfoTO.getSkuAttrValues());

        try {
            String  jsonString = objectMapper.writeValueAsString(cartItem);
            boundGeoOperations.put(skuId.toString(), jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Result.ok(skuInfoTO);
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

    private BoundHashOperations<String, Object, Object> getCartItem() {
        Long memberId = LoginProtectedInterceptor.threadLocal.get();
        String key = "jxmall-cart:" + memberId;
        BoundHashOperations<String, Object, Object> boundGeoOperations = stringRedisTemplate.boundHashOps(key);

        // 检查 boundGeoOperations 是否为 null


            return boundGeoOperations;

    }

}
