package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.BrandTO;

@FeignClient(value = "cloud-gateway-service")
public interface BrandOpenFeignApi {

    @PostMapping("/product/Brand/list/page")
    public TableDataInfo list(@RequestBody PageRequest<BrandTO> pageRequest);

    /**
     * 获取品牌详细信息
     */
    @GetMapping(value = "/product/Brand/info")
    public Result<BrandTO> getInfo(@RequestParam("brandId") Long brandId);
    /**
     * 新增品牌
     */
    @PostMapping("/product/Brand/save")
    public Result<?> add(@RequestBody BrandTO brandTO);

    /**
     * 修改品牌
     */
    @PutMapping("/product/Brand/update")
    public Result<?> edit(@RequestBody BrandTO brandTO);

    /**
     * 删除品牌
     */
    @DeleteMapping("/product/Brand/delete")
    public Result<?> remove(@RequestBody Long[] brandIds);

}