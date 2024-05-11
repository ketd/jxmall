package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SkuImagesTO;

@FeignClient(value = "cloud-gateway-service")
public interface SkuImagesOpenFeignApi {

    @PostMapping("/product/SkuImages/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuImagesTO> pageRequest);

    /**
     * 获取sku图片详细信息
     */
    @GetMapping(value = "/product/SkuImages/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增sku图片
     */
    @PostMapping("/product/SkuImages/save")
    public Result<?> add(@RequestBody SkuImagesTO skuImagesTO);

    /**
     * 修改sku图片
     */
    @PutMapping("/product/SkuImages/update")
    public Result<?> edit(@RequestBody SkuImagesTO skuImagesTO);

    /**
     * 删除sku图片
     */
    @DeleteMapping("/product/SkuImages/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}