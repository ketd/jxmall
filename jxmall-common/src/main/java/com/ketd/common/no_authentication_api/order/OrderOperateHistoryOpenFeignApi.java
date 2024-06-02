package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.OrderOperateHistoryTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-gateway-service")
public interface OrderOperateHistoryOpenFeignApi {

    @PostMapping("/order/OrderOperateHistory/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderOperateHistoryTO> pageRequest);

    /**
     * 获取订单操作历史记录详细信息
     */
    @GetMapping(value = "/order/OrderOperateHistory/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增订单操作历史记录
     */
    @PostMapping("/order/OrderOperateHistory/save")
    public Result<?> add(@RequestBody OrderOperateHistoryTO orderOperateHistoryTO);

    /**
     * 修改订单操作历史记录
     */
    @PutMapping("/order/OrderOperateHistory/update")
    public Result<?> edit(@RequestBody OrderOperateHistoryTO orderOperateHistoryTO);

    /**
     * 删除订单操作历史记录
     */
    @DeleteMapping("/order/OrderOperateHistory/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}