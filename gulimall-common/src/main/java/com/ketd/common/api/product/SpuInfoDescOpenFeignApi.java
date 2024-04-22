package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SpuInfoDescTO;

@FeignClient(value = "cloud-gateway-service")
public interface SpuInfoDescOpenFeignApi {

    @PostMapping("/product/SpuInfoDesc/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuInfoDescTO> pageRequest);

    /**
     * 获取spu信息介绍详细信息
     */
    @GetMapping(value = "/product/SpuInfoDesc/info")
    public Result<?> getInfo(@RequestParam("spuId") Long spuId);
    /**
     * 新增spu信息介绍
     */
    @PostMapping("/product/SpuInfoDesc/save")
    public Result<?> add(@RequestBody SpuInfoDescTO spuInfoDescTO);

    /**
     * 修改spu信息介绍
     */
    @PutMapping("/product/SpuInfoDesc/update")
    public Result<?> edit(@RequestBody SpuInfoDescTO spuInfoDescTO);

    /**
     * 删除spu信息介绍
     */
    @DeleteMapping("/product/SpuInfoDesc/delete")
    public Result<?> remove(@RequestBody Long[] spuIds);

}