package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SpuCommentTO;

@FeignClient(value = "cloud-gateway-service")
public interface SpuCommentOpenFeignApi {

    @PostMapping("/product/SpuComment/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuCommentTO> pageRequest);

    /**
     * 获取商品评价详细信息
     */
    @GetMapping(value = "/product/SpuComment/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品评价
     */
    @PostMapping("/product/SpuComment/save")
    public Result<?> add(@RequestBody SpuCommentTO spuCommentTO);

    /**
     * 修改商品评价
     */
    @PutMapping("/product/SpuComment/update")
    public Result<?> edit(@RequestBody SpuCommentTO spuCommentTO);

    /**
     * 删除商品评价
     */
    @DeleteMapping("/product/SpuComment/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}