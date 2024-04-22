package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SkuInfoTO;

@FeignClient(value = "cloud-gateway-service")
public interface SkuInfoOpenFeignApi {

    @PostMapping("/product/SkuInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuInfoTO> pageRequest);

    /**
     * 获取sku信息详细信息
     */
    @GetMapping(value = "/product/SkuInfo/info")
    public Result<SkuInfoTO> getInfo(@RequestParam("skuId") Long skuId);
    /**
     * 新增sku信息
     */
    @PostMapping("/product/SkuInfo/save")
    public Result<?> add(@RequestBody SkuInfoTO skuInfoTO);

    /**
     * 修改sku信息
     */
    @PutMapping("/product/SkuInfo/update")
    public Result<?> edit(@RequestBody SkuInfoTO skuInfoTO);

    /**
     * 删除sku信息
     */
    @DeleteMapping("/product/SkuInfo/delete")
    public Result<?> remove(@RequestBody Long[] skuIds);

}