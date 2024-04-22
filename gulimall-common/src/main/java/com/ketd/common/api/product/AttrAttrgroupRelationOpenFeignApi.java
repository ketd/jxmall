package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.AttrAttrgroupRelationTO;

@FeignClient(value = "cloud-gateway-service")
public interface AttrAttrgroupRelationOpenFeignApi {

    @PostMapping("/product/AttrAttrgroupRelation/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrAttrgroupRelationTO> pageRequest);

    /**
     * 获取属性&属性分组关联详细信息
     */
    @GetMapping(value = "/product/AttrAttrgroupRelation/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增属性&属性分组关联
     */
    @PostMapping("/product/AttrAttrgroupRelation/save")
    public Result<?> add(@RequestBody AttrAttrgroupRelationTO attrAttrgroupRelationTO);

    /**
     * 修改属性&属性分组关联
     */
    @PutMapping("/product/AttrAttrgroupRelation/update")
    public Result<?> edit(@RequestBody AttrAttrgroupRelationTO attrAttrgroupRelationTO);

    /**
     * 删除属性&属性分组关联
     */
    @DeleteMapping("/product/AttrAttrgroupRelation/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}