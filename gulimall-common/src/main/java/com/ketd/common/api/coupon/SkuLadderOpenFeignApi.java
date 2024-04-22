package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SkuLadderTO;

@FeignClient(value = "cloud-gateway-service")
public interface SkuLadderOpenFeignApi {

    @PostMapping("/coupon/SkuLadder/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuLadderTO> pageRequest);

    /**
     * 获取商品阶梯价格详细信息
     */
    @GetMapping(value = "/coupon/SkuLadder/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品阶梯价格
     */
    @PostMapping("/coupon/SkuLadder/save")
    public Result<?> add(@RequestBody SkuLadderTO skuLadderTO);

    /**
     * 修改商品阶梯价格
     */
    @PutMapping("/coupon/SkuLadder/update")
    public Result<?> edit(@RequestBody SkuLadderTO skuLadderTO);

    /**
     * 删除商品阶梯价格
     */
    @DeleteMapping("/coupon/SkuLadder/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}