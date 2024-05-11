package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.CouponTO;

@FeignClient(value = "cloud-gateway-service")
public interface CouponOpenFeignApi {

    @PostMapping("/coupon/Coupon/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponTO> pageRequest);

    /**
     * 获取优惠券信息详细信息
     */
    @GetMapping(value = "/coupon/Coupon/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增优惠券信息
     */
    @PostMapping("/coupon/Coupon/save")
    public Result<?> add(@RequestBody CouponTO couponTO);

    /**
     * 修改优惠券信息
     */
    @PutMapping("/coupon/Coupon/update")
    public Result<?> edit(@RequestBody CouponTO couponTO);

    /**
     * 删除优惠券信息
     */
    @DeleteMapping("/coupon/Coupon/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}