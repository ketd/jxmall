package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.HomeAdvTO;

@FeignClient(value = "cloud-gateway-service")
public interface HomeAdvOpenFeignApi {

    @PostMapping("/coupon/HomeAdv/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeAdvTO> pageRequest);

    /**
     * 获取首页轮播广告详细信息
     */
    @GetMapping(value = "/coupon/HomeAdv/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增首页轮播广告
     */
    @PostMapping("/coupon/HomeAdv/save")
    public Result<?> add(@RequestBody HomeAdvTO homeAdvTO);

    /**
     * 修改首页轮播广告
     */
    @PutMapping("/coupon/HomeAdv/update")
    public Result<?> edit(@RequestBody HomeAdvTO homeAdvTO);

    /**
     * 删除首页轮播广告
     */
    @DeleteMapping("/coupon/HomeAdv/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}