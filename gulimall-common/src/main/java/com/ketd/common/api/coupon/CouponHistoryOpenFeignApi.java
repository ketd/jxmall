package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.CouponHistoryTO;

@FeignClient(value = "cloud-gateway-service")
public interface CouponHistoryOpenFeignApi {

    @PostMapping("/coupon/CouponHistory/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponHistoryTO> pageRequest);

    /**
     * 获取优惠券领取历史记录详细信息
     */
    @GetMapping(value = "/coupon/CouponHistory/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增优惠券领取历史记录
     */
    @PostMapping("/coupon/CouponHistory/save")
    public Result<?> add(@RequestBody CouponHistoryTO couponHistoryTO);

    /**
     * 修改优惠券领取历史记录
     */
    @PutMapping("/coupon/CouponHistory/update")
    public Result<?> edit(@RequestBody CouponHistoryTO couponHistoryTO);

    /**
     * 删除优惠券领取历史记录
     */
    @DeleteMapping("/coupon/CouponHistory/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}