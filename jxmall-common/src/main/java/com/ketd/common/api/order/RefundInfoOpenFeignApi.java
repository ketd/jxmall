package com.ketd.common.api.order;

import com.ketd.common.domain.order.WareSkuLockTo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.order.RefundInfoTO;

@FeignClient(value = "cloud-gateway-service")
public interface RefundInfoOpenFeignApi {

    @PostMapping("/order/RefundInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<RefundInfoTO> pageRequest);

    /**
     * 获取退款信息详细信息
     */
    @GetMapping(value = "/order/RefundInfo/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增退款信息
     */
    @PostMapping("/order/RefundInfo/save")
    public Result<?> add(@RequestBody RefundInfoTO refundInfoTO);

    /**
     * 修改退款信息
     */
    @PutMapping("/order/RefundInfo/update")
    public Result<?> edit(@RequestBody RefundInfoTO refundInfoTO);

    /**
     * 删除退款信息
     */
    @DeleteMapping("/order/RefundInfo/delete")
    public Result<?> remove(@RequestBody Long[] ids);




}