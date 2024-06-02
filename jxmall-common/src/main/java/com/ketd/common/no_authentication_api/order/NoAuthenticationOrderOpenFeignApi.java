package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.OrderTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationOrderOpenFeignApi {

    @PostMapping("/order/Order/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderTO> pageRequest);

    /**
     * 获取订单详细信息
     */
    @GetMapping(value = "/order/Order/info")
    public Result<OrderTO> getInfo(@RequestParam("id") Long id);

    @GetMapping(value = "/order/Order/infoByOrderSn")
    public Result<OrderTO> getInfoByOrderSn(@RequestParam("orderSn") String orderSn);
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