package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.IntegrationChangeHistoryTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationIntegrationChangeHistoryOpenFeignApi {

    @PostMapping("/member/IntegrationChangeHistory/list/page")
    public TableDataInfo list(@RequestBody PageRequest<IntegrationChangeHistoryTO> pageRequest);

    /**
     * 获取积分变化历史记录详细信息
     */
    @GetMapping(value = "/member/IntegrationChangeHistory/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增积分变化历史记录
     */
    @PostMapping("/member/IntegrationChangeHistory/save")
    public Result<?> add(@RequestBody IntegrationChangeHistoryTO integrationChangeHistoryTO);

    /**
     * 修改积分变化历史记录
     */
    @PutMapping("/member/IntegrationChangeHistory/update")
    public Result<?> edit(@RequestBody IntegrationChangeHistoryTO integrationChangeHistoryTO);

    /**
     * 删除积分变化历史记录
     */
    @DeleteMapping("/member/IntegrationChangeHistory/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}