package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.CategoryTO;

@FeignClient(value = "cloud-gateway-service")
public interface CategoryOpenFeignApi {

    @PostMapping("/product/Category/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CategoryTO> pageRequest);

    /**
     * 获取商品三级分类详细信息
     */
    @GetMapping(value = "/product/Category/info")
    public Result<?> getInfo(@RequestParam("catId") Long catId);
    /**
     * 新增商品三级分类
     */
    @PostMapping("/product/Category/save")
    public Result<?> add(@RequestBody CategoryTO categoryTO);

    /**
     * 修改商品三级分类
     */
    @PutMapping("/product/Category/update")
    public Result<?> edit(@RequestBody CategoryTO categoryTO);

    /**
     * 删除商品三级分类
     */
    @DeleteMapping("/product/Category/delete")
    public Result<?> remove(@RequestBody Long[] catIds);

}