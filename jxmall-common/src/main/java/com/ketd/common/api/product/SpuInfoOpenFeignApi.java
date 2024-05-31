package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SpuInfoTO;

@FeignClient(value = "cloud-gateway-service")
public interface SpuInfoOpenFeignApi {

    @PostMapping("/product/SpuInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuInfoTO> pageRequest);

    /**
     * 获取spu信息详细信息
     */
    @GetMapping(value = "/product/SpuInfo/info")
    public Result<SpuInfoTO> getInfo(@RequestParam("id") Long id);
    /**
     * 新增spu信息
     */
    @PostMapping("/product/SpuInfo/save")
    public Result<?> add(@RequestBody SpuInfoTO spuInfoTO);

    /**
     * 修改spu信息
     */
    @PutMapping("/product/SpuInfo/update")
    public Result<?> edit(@RequestBody SpuInfoTO spuInfoTO);

    /**
     * 删除spu信息
     */
    @DeleteMapping("/product/SpuInfo/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}