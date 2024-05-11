package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.AttrTO;

@FeignClient(value = "cloud-gateway-service")
public interface AttrOpenFeignApi {

    @PostMapping("/product/Attr/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrTO> pageRequest);

    /**
     * 获取商品属性详细信息
     */
    @GetMapping(value = "/product/Attr/info")
    public Result<?> getInfo(@RequestParam("attrId") Long attrId);
    /**
     * 新增商品属性
     */
    @PostMapping("/product/Attr/save")
    public Result<?> add(@RequestBody AttrTO attrTO);

    /**
     * 修改商品属性
     */
    @PutMapping("/product/Attr/update")
    public Result<?> edit(@RequestBody AttrTO attrTO);

    /**
     * 删除商品属性
     */
    @DeleteMapping("/product/Attr/delete")
    public Result<?> remove(@RequestBody Long[] attrIds);

}