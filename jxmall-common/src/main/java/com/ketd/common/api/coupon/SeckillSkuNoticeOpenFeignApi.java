package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SeckillSkuNoticeTO;

@FeignClient(value = "cloud-gateway-service")
public interface SeckillSkuNoticeOpenFeignApi {

    @PostMapping("/coupon/SeckillSkuNotice/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSkuNoticeTO> pageRequest);

    /**
     * 获取秒杀商品通知订阅详细信息
     */
    @GetMapping(value = "/coupon/SeckillSkuNotice/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增秒杀商品通知订阅
     */
    @PostMapping("/coupon/SeckillSkuNotice/save")
    public Result<?> add(@RequestBody SeckillSkuNoticeTO seckillSkuNoticeTO);

    /**
     * 修改秒杀商品通知订阅
     */
    @PutMapping("/coupon/SeckillSkuNotice/update")
    public Result<?> edit(@RequestBody SeckillSkuNoticeTO seckillSkuNoticeTO);

    /**
     * 删除秒杀商品通知订阅
     */
    @DeleteMapping("/coupon/SeckillSkuNotice/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}