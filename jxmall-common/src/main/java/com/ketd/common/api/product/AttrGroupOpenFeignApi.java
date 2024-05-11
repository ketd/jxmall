package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.AttrGroupTO;

@FeignClient(value = "cloud-gateway-service")
public interface AttrGroupOpenFeignApi {

    @PostMapping("/product/AttrGroup/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrGroupTO> pageRequest);

    /**
     * 获取属性分组详细信息
     */
    @GetMapping(value = "/product/AttrGroup/info")
    public Result<?> getInfo(@RequestParam("attrGroupId") Long attrGroupId);
    /**
     * 新增属性分组
     */
    @PostMapping("/product/AttrGroup/save")
    public Result<?> add(@RequestBody AttrGroupTO attrGroupTO);

    /**
     * 修改属性分组
     */
    @PutMapping("/product/AttrGroup/update")
    public Result<?> edit(@RequestBody AttrGroupTO attrGroupTO);

    /**
     * 删除属性分组
     */
    @DeleteMapping("/product/AttrGroup/delete")
    public Result<?> remove(@RequestBody Long[] attrGroupIds);

}