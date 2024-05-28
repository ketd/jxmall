package com.ketd.common.api.order;

import com.ketd.common.domain.order.OrderConstTo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.order.OrderItemTO;

import java.util.List;

@FeignClient(value = "cloud-gateway-service")
public interface OrderItemOpenFeignApi {

    @PostMapping("/order/OrderItem/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderItemTO> pageRequest);


    @PostMapping("/order/OrderItem/toTrade")
    public Result<?> toTrade(@RequestBody List<OrderConstTo> orderConstVos);
    /**
     * 获取订单项信息详细信息
     */
    @GetMapping(value = "/order/OrderItem/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增订单项信息
     */
    @PostMapping("/order/OrderItem/save")
    public Result<?> add(@RequestBody OrderItemTO orderItemTO);

    /**
     * 修改订单项信息
     */
    @PutMapping("/order/OrderItem/update")
    public Result<?> edit(@RequestBody OrderItemTO orderItemTO);

    /**
     * 删除订单项信息
     */
    @DeleteMapping("/order/OrderItem/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}