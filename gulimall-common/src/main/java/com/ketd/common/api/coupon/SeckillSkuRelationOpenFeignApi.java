package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SeckillSkuRelationTO;

@FeignClient(value = "cloud-gateway-service")
public interface SeckillSkuRelationOpenFeignApi {

    @PostMapping("/coupon/SeckillSkuRelation/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSkuRelationTO> pageRequest);

    /**
     * 获取秒杀活动商品关联详细信息
     */
    @GetMapping(value = "/coupon/SeckillSkuRelation/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增秒杀活动商品关联
     */
    @PostMapping("/coupon/SeckillSkuRelation/save")
    public Result<?> add(@RequestBody SeckillSkuRelationTO seckillSkuRelationTO);

    /**
     * 修改秒杀活动商品关联
     */
    @PutMapping("/coupon/SeckillSkuRelation/update")
    public Result<?> edit(@RequestBody SeckillSkuRelationTO seckillSkuRelationTO);

    /**
     * 删除秒杀活动商品关联
     */
    @DeleteMapping("/coupon/SeckillSkuRelation/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}