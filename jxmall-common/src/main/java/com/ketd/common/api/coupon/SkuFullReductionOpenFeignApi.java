package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SkuFullReductionTO;

@FeignClient(value = "cloud-gateway-service")
public interface SkuFullReductionOpenFeignApi {

    @PostMapping("/coupon/SkuFullReduction/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuFullReductionTO> pageRequest);

    /**
     * 获取商品满减信息详细信息
     */
    @GetMapping(value = "/coupon/SkuFullReduction/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品满减信息
     */
    @PostMapping("/coupon/SkuFullReduction/save")
    public Result<?> add(@RequestBody SkuFullReductionTO skuFullReductionTO);

    /**
     * 修改商品满减信息
     */
    @PutMapping("/coupon/SkuFullReduction/update")
    public Result<?> edit(@RequestBody SkuFullReductionTO skuFullReductionTO);

    /**
     * 删除商品满减信息
     */
    @DeleteMapping("/coupon/SkuFullReduction/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}