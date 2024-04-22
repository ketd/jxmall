package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SeckillPromotionTO;

@FeignClient(value = "cloud-gateway-service")
public interface SeckillPromotionOpenFeignApi {

    @PostMapping("/coupon/SeckillPromotion/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillPromotionTO> pageRequest);

    /**
     * 获取秒杀活动详细信息
     */
    @GetMapping(value = "/coupon/SeckillPromotion/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增秒杀活动
     */
    @PostMapping("/coupon/SeckillPromotion/save")
    public Result<?> add(@RequestBody SeckillPromotionTO seckillPromotionTO);

    /**
     * 修改秒杀活动
     */
    @PutMapping("/coupon/SeckillPromotion/update")
    public Result<?> edit(@RequestBody SeckillPromotionTO seckillPromotionTO);

    /**
     * 删除秒杀活动
     */
    @DeleteMapping("/coupon/SeckillPromotion/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}