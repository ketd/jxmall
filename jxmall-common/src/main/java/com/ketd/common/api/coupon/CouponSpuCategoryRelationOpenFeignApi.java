package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.CouponSpuCategoryRelationTO;

@FeignClient(value = "cloud-gateway-service")
public interface CouponSpuCategoryRelationOpenFeignApi {

    @PostMapping("/coupon/CouponSpuCategoryRelation/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponSpuCategoryRelationTO> pageRequest);

    /**
     * 获取优惠券分类关联详细信息
     */
    @GetMapping(value = "/coupon/CouponSpuCategoryRelation/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增优惠券分类关联
     */
    @PostMapping("/coupon/CouponSpuCategoryRelation/save")
    public Result<?> add(@RequestBody CouponSpuCategoryRelationTO couponSpuCategoryRelationTO);

    /**
     * 修改优惠券分类关联
     */
    @PutMapping("/coupon/CouponSpuCategoryRelation/update")
    public Result<?> edit(@RequestBody CouponSpuCategoryRelationTO couponSpuCategoryRelationTO);

    /**
     * 删除优惠券分类关联
     */
    @DeleteMapping("/coupon/CouponSpuCategoryRelation/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}