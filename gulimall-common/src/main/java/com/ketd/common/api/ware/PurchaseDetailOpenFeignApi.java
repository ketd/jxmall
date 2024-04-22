package com.ketd.common.api.ware;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.PurchaseDetailTO;

@FeignClient(value = "cloud-gateway-service")
public interface PurchaseDetailOpenFeignApi {

    @PostMapping("/ware/PurchaseDetail/list/page")
    public TableDataInfo list(@RequestBody PageRequest<PurchaseDetailTO> pageRequest);

    /**
     * 获取采购详情详细信息
     */
    @GetMapping(value = "/ware/PurchaseDetail/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增采购详情
     */
    @PostMapping("/ware/PurchaseDetail/save")
    public Result<?> add(@RequestBody PurchaseDetailTO purchaseDetailTO);

    /**
     * 修改采购详情
     */
    @PutMapping("/ware/PurchaseDetail/update")
    public Result<?> edit(@RequestBody PurchaseDetailTO purchaseDetailTO);

    /**
     * 删除采购详情
     */
    @DeleteMapping("/ware/PurchaseDetail/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}