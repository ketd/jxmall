package com.ketd.common.api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.GrowthChangeHistoryTO;

@FeignClient(value = "cloud-gateway-service")
public interface GrowthChangeHistoryOpenFeignApi {

    @PostMapping("/member/GrowthChangeHistory/list/page")
    public TableDataInfo list(@RequestBody PageRequest<GrowthChangeHistoryTO> pageRequest);

    /**
     * 获取成长值变化历史记录详细信息
     */
    @GetMapping(value = "/member/GrowthChangeHistory/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增成长值变化历史记录
     */
    @PostMapping("/member/GrowthChangeHistory/save")
    public Result<?> add(@RequestBody GrowthChangeHistoryTO growthChangeHistoryTO);

    /**
     * 修改成长值变化历史记录
     */
    @PutMapping("/member/GrowthChangeHistory/update")
    public Result<?> edit(@RequestBody GrowthChangeHistoryTO growthChangeHistoryTO);

    /**
     * 删除成长值变化历史记录
     */
    @DeleteMapping("/member/GrowthChangeHistory/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}