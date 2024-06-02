package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.OrderReturnReasonTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-gateway-service")
public interface OrderReturnReasonOpenFeignApi {

    @PostMapping("/order/OrderReturnReason/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderReturnReasonTO> pageRequest);

    /**
     * 获取退货原因详细信息
     */
    @GetMapping(value = "/order/OrderReturnReason/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增退货原因
     */
    @PostMapping("/order/OrderReturnReason/save")
    public Result<?> add(@RequestBody OrderReturnReasonTO orderReturnReasonTO);

    /**
     * 修改退货原因
     */
    @PutMapping("/order/OrderReturnReason/update")
    public Result<?> edit(@RequestBody OrderReturnReasonTO orderReturnReasonTO);

    /**
     * 删除退货原因
     */
    @DeleteMapping("/order/OrderReturnReason/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}