package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.CouponSpuRelationTO;

@FeignClient(value = "cloud-gateway-service")
public interface CouponSpuRelationOpenFeignApi {

    @PostMapping("/coupon/CouponSpuRelation/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponSpuRelationTO> pageRequest);

    /**
     * 获取优惠券与产品关联详细信息
     */
    @GetMapping(value = "/coupon/CouponSpuRelation/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增优惠券与产品关联
     */
    @PostMapping("/coupon/CouponSpuRelation/save")
    public Result<?> add(@RequestBody CouponSpuRelationTO couponSpuRelationTO);

    /**
     * 修改优惠券与产品关联
     */
    @PutMapping("/coupon/CouponSpuRelation/update")
    public Result<?> edit(@RequestBody CouponSpuRelationTO couponSpuRelationTO);

    /**
     * 删除优惠券与产品关联
     */
    @DeleteMapping("/coupon/CouponSpuRelation/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}