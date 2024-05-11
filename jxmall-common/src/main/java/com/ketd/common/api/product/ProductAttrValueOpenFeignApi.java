package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.ProductAttrValueTO;

@FeignClient(value = "cloud-gateway-service")
public interface ProductAttrValueOpenFeignApi {

    @PostMapping("/product/ProductAttrValue/list/page")
    public TableDataInfo list(@RequestBody PageRequest<ProductAttrValueTO> pageRequest);

    /**
     * 获取spu属性值详细信息
     */
    @GetMapping(value = "/product/ProductAttrValue/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增spu属性值
     */
    @PostMapping("/product/ProductAttrValue/save")
    public Result<?> add(@RequestBody ProductAttrValueTO productAttrValueTO);

    /**
     * 修改spu属性值
     */
    @PutMapping("/product/ProductAttrValue/update")
    public Result<?> edit(@RequestBody ProductAttrValueTO productAttrValueTO);

    /**
     * 删除spu属性值
     */
    @DeleteMapping("/product/ProductAttrValue/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}