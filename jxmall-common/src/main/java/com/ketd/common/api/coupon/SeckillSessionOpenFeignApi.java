package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SeckillSessionTO;

@FeignClient(value = "cloud-gateway-service")
public interface SeckillSessionOpenFeignApi {

    @PostMapping("/coupon/SeckillSession/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSessionTO> pageRequest);

    /**
     * 获取秒杀活动场次详细信息
     */
    @GetMapping(value = "/coupon/SeckillSession/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增秒杀活动场次
     */
    @PostMapping("/coupon/SeckillSession/save")
    public Result<?> add(@RequestBody SeckillSessionTO seckillSessionTO);

    /**
     * 修改秒杀活动场次
     */
    @PutMapping("/coupon/SeckillSession/update")
    public Result<?> edit(@RequestBody SeckillSessionTO seckillSessionTO);

    /**
     * 删除秒杀活动场次
     */
    @DeleteMapping("/coupon/SeckillSession/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}