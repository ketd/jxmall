package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.SpuBoundsTO;

@FeignClient(value = "cloud-gateway-service")
public interface SpuBoundsOpenFeignApi {

    @PostMapping("/coupon/SpuBounds/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuBoundsTO> pageRequest);

    /**
     * 获取商品spu积分设置详细信息
     */
    @GetMapping(value = "/coupon/SpuBounds/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品spu积分设置
     */
    @PostMapping("/coupon/SpuBounds/save")
    public Result<?> add(@RequestBody SpuBoundsTO spuBoundsTO);

    /**
     * 修改商品spu积分设置
     */
    @PutMapping("/coupon/SpuBounds/update")
    public Result<?> edit(@RequestBody SpuBoundsTO spuBoundsTO);

    /**
     * 删除商品spu积分设置
     */
    @DeleteMapping("/coupon/SpuBounds/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}