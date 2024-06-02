package com.ketd.common.no_authentication_api.order;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.order.PaymentInfoTO;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-gateway-service")
public interface PaymentInfoOpenFeignApi {

    @PostMapping("/order/PaymentInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<PaymentInfoTO> pageRequest);

    /**
     * 获取支付信息详细信息
     */
    @GetMapping(value = "/order/PaymentInfo/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增支付信息
     */
    @PostMapping("/order/PaymentInfo/save")
    public Result<?> add(@RequestBody PaymentInfoTO paymentInfoTO);

    /**
     * 修改支付信息
     */
    @PutMapping("/order/PaymentInfo/update")
    public Result<?> edit(@RequestBody PaymentInfoTO paymentInfoTO);

    /**
     * 删除支付信息
     */
    @DeleteMapping("/order/PaymentInfo/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}