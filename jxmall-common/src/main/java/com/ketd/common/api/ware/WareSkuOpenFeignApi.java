package com.ketd.common.api.ware;

import com.ketd.common.domain.order.LockStickResult;
import com.ketd.common.domain.order.WareSkuLockTo;
import com.ketd.common.domain.ware.HasStockTo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.WareSkuTO;

import java.util.List;

@FeignClient(value = "cloud-gateway-service")
public interface WareSkuOpenFeignApi {

    @PostMapping("/ware/WareSku/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareSkuTO> pageRequest);

    /**
     * 获取商品库存详细信息
     */
    @GetMapping(value = "/ware/WareSku/info")
    public Result<?> getInfo(@RequestParam("id") Long id);


    /**
     * 新增商品库存
     */
    @PostMapping("/ware/WareSku/save")
    public Result<?> add(@RequestBody WareSkuTO wareSkuTO);

    @PostMapping("/ware/WareSku/save/list")
    public Result<?> addList(@RequestBody List<WareSkuTO>  wareSkuTOList);

    /**
     * 修改商品库存
     */
    @PutMapping("/ware/WareSku/update")
    public Result<?> edit(@RequestBody WareSkuTO wareSkuTO);

    /**
     * 删除商品库存
     */
    @DeleteMapping("/ware/WareSku/delete")
    public Result<?> remove(@RequestBody Long[] ids);

    @PostMapping("/ware/WareSku/hasStock")
    public Result<List<HasStockTo>> hasStock(@RequestBody List<Long> skuIds);

    @GetMapping("/ware/WareSku/hasStockByCount")
    public Result<Boolean> hasStockByCount(@RequestParam(value = "skuId") Long skuId,@RequestParam(value = "count") Integer count);


    @PostMapping("/ware/WareSku/lockStock/order")
    public  Result<List<LockStickResult>> orderLockStock(@RequestBody WareSkuLockTo wareSkuLockTo);
}