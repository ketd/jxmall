package com.ketd.common.api.ware;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.ware.WareInfoTO;

@FeignClient(value = "cloud-gateway-service")
public interface WareInfoOpenFeignApi {

    @PostMapping("/ware/WareInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareInfoTO> pageRequest);

    /**
     * 获取仓库信息详细信息
     */
    @GetMapping(value = "/ware/WareInfo/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增仓库信息
     */
    @PostMapping("/ware/WareInfo/save")
    public Result<?> add(@RequestBody WareInfoTO wareInfoTO);

    /**
     * 修改仓库信息
     */
    @PutMapping("/ware/WareInfo/update")
    public Result<?> edit(@RequestBody WareInfoTO wareInfoTO);

    /**
     * 删除仓库信息
     */
    @DeleteMapping("/ware/WareInfo/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}