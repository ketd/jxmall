package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.OrderReturnApplyTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-gateway-service")
public interface OrderReturnApplyOpenFeignApi {

    @PostMapping("/order/OrderReturnApply/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderReturnApplyTO> pageRequest);

    /**
     * 获取订单退货申请详细信息
     */
    @GetMapping(value = "/order/OrderReturnApply/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增订单退货申请
     */
    @PostMapping("/order/OrderReturnApply/save")
    public Result<?> add(@RequestBody OrderReturnApplyTO orderReturnApplyTO);

    /**
     * 修改订单退货申请
     */
    @PutMapping("/order/OrderReturnApply/update")
    public Result<?> edit(@RequestBody OrderReturnApplyTO orderReturnApplyTO);

    /**
     * 删除订单退货申请
     */
    @DeleteMapping("/order/OrderReturnApply/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}