package com.ketd.common.api.ware;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.WareOrderTaskTO;

@FeignClient(value = "cloud-gateway-service")
public interface WareOrderTaskOpenFeignApi {

    @PostMapping("/ware/WareOrderTask/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareOrderTaskTO> pageRequest);

    /**
     * 获取库存工作单详细信息
     */
    @GetMapping(value = "/ware/WareOrderTask/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增库存工作单
     */
    @PostMapping("/ware/WareOrderTask/save")
    public Result<?> add(@RequestBody WareOrderTaskTO wareOrderTaskTO);

    /**
     * 修改库存工作单
     */
    @PutMapping("/ware/WareOrderTask/update")
    public Result<?> edit(@RequestBody WareOrderTaskTO wareOrderTaskTO);

    /**
     * 删除库存工作单
     */
    @DeleteMapping("/ware/WareOrderTask/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}