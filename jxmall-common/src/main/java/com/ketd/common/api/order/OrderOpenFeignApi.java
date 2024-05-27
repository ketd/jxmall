package com.ketd.common.api.order;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.order.OrderTO;

@FeignClient(value = "cloud-gateway-service")
public interface OrderOpenFeignApi {

    @PostMapping("/order/Order/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderTO> pageRequest);

    /**
     * 获取订单详细信息
     */
    @GetMapping(value = "/order/Order/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增订单
     */
    @PostMapping("/order/Order/save")
    public Result<?> add(@RequestBody OrderTO orderTO);

    /**
     * 修改订单
     */
    @PutMapping("/order/Order/update")
    public Result<?> edit(@RequestBody OrderTO orderTO);

    /**
     * 删除订单
     */
    @DeleteMapping("/order/Order/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}