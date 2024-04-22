package com.ketd.common.api.ware;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.WareOrderTaskDetailTO;

@FeignClient(value = "cloud-gateway-service")
public interface WareOrderTaskDetailOpenFeignApi {

    @PostMapping("/ware/WareOrderTaskDetail/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareOrderTaskDetailTO> pageRequest);

    /**
     * 获取库存工作单详细信息
     */
    @GetMapping(value = "/ware/WareOrderTaskDetail/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增库存工作单
     */
    @PostMapping("/ware/WareOrderTaskDetail/save")
    public Result<?> add(@RequestBody WareOrderTaskDetailTO wareOrderTaskDetailTO);

    /**
     * 修改库存工作单
     */
    @PutMapping("/ware/WareOrderTaskDetail/update")
    public Result<?> edit(@RequestBody WareOrderTaskDetailTO wareOrderTaskDetailTO);

    /**
     * 删除库存工作单
     */
    @DeleteMapping("/ware/WareOrderTaskDetail/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}