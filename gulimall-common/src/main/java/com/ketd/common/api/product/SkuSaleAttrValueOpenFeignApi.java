package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SkuSaleAttrValueTO;

@FeignClient(value = "cloud-gateway-service")
public interface SkuSaleAttrValueOpenFeignApi {

    @PostMapping("/product/SkuSaleAttrValue/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuSaleAttrValueTO> pageRequest);

    /**
     * 获取sku销售属性&值详细信息
     */
    @GetMapping(value = "/product/SkuSaleAttrValue/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增sku销售属性&值
     */
    @PostMapping("/product/SkuSaleAttrValue/save")
    public Result<?> add(@RequestBody SkuSaleAttrValueTO skuSaleAttrValueTO);

    /**
     * 修改sku销售属性&值
     */
    @PutMapping("/product/SkuSaleAttrValue/update")
    public Result<?> edit(@RequestBody SkuSaleAttrValueTO skuSaleAttrValueTO);

    /**
     * 删除sku销售属性&值
     */
    @DeleteMapping("/product/SkuSaleAttrValue/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}