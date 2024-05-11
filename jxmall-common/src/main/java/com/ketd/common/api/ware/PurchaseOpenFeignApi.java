package com.ketd.common.api.ware;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.PurchaseTO;

@FeignClient(value = "cloud-gateway-service")
public interface PurchaseOpenFeignApi {

    @PostMapping("/ware/Purchase/list/page")
    public TableDataInfo list(@RequestBody PageRequest<PurchaseTO> pageRequest);

    /**
     * 获取采购信息详细信息
     */
    @GetMapping(value = "/ware/Purchase/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增采购信息
     */
    @PostMapping("/ware/Purchase/save")
    public Result<?> add(@RequestBody PurchaseTO purchaseTO);

    /**
     * 修改采购信息
     */
    @PutMapping("/ware/Purchase/update")
    public Result<?> edit(@RequestBody PurchaseTO purchaseTO);

    /**
     * 删除采购信息
     */
    @DeleteMapping("/ware/Purchase/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}