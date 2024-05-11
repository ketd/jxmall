package com.ketd.common.api.product;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.product.SpuImagesTO;

@FeignClient(value = "cloud-gateway-service")
public interface SpuImagesOpenFeignApi {

    @PostMapping("/product/SpuImages/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuImagesTO> pageRequest);

    /**
     * 获取spu图片详细信息
     */
    @GetMapping(value = "/product/SpuImages/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增spu图片
     */
    @PostMapping("/product/SpuImages/save")
    public Result<?> add(@RequestBody SpuImagesTO spuImagesTO);

    /**
     * 修改spu图片
     */
    @PutMapping("/product/SpuImages/update")
    public Result<?> edit(@RequestBody SpuImagesTO spuImagesTO);

    /**
     * 删除spu图片
     */
    @DeleteMapping("/product/SpuImages/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}