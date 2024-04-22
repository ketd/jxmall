package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.CategoryBrandRelationTO;

@FeignClient(value = "cloud-gateway-service")
public interface CategoryBrandRelationOpenFeignApi {

    @PostMapping("/product/CategoryBrandRelation/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CategoryBrandRelationTO> pageRequest);

    /**
     * 获取品牌分类关联详细信息
     */
    @GetMapping(value = "/product/CategoryBrandRelation/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增品牌分类关联
     */
    @PostMapping("/product/CategoryBrandRelation/save")
    public Result<?> add(@RequestBody CategoryBrandRelationTO categoryBrandRelationTO);

    /**
     * 修改品牌分类关联
     */
    @PutMapping("/product/CategoryBrandRelation/update")
    public Result<?> edit(@RequestBody CategoryBrandRelationTO categoryBrandRelationTO);

    /**
     * 删除品牌分类关联
     */
    @DeleteMapping("/product/CategoryBrandRelation/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}