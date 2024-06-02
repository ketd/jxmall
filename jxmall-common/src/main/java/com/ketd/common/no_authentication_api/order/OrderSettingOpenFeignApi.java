package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.OrderSettingTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-gateway-service")
public interface OrderSettingOpenFeignApi {

    @PostMapping("/order/OrderSetting/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderSettingTO> pageRequest);

    /**
     * 获取订单配置信息详细信息
     */
    @GetMapping(value = "/order/OrderSetting/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增订单配置信息
     */
    @PostMapping("/order/OrderSetting/save")
    public Result<?> add(@RequestBody OrderSettingTO orderSettingTO);

    /**
     * 修改订单配置信息
     */
    @PutMapping("/order/OrderSetting/update")
    public Result<?> edit(@RequestBody OrderSettingTO orderSettingTO);

    /**
     * 删除订单配置信息
     */
    @DeleteMapping("/order/OrderSetting/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}