package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.BalanceDetailsTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationBalanceDetailsOpenFeignApi {

    @PostMapping("/member/BalanceDetails/list/page")
    public TableDataInfo list(@RequestBody PageRequest<BalanceDetailsTO> pageRequest);

    /**
     * 获取用户余额明细详细信息
     */
    @GetMapping(value = "/member/BalanceDetails/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增用户余额明细
     */
    @PostMapping("/member/BalanceDetails/save")
    public Result<?> add(@RequestBody BalanceDetailsTO balanceDetailsTO);

    /**
     * 修改用户余额明细
     */
    @PutMapping("/member/BalanceDetails/update")
    public Result<?> edit(@RequestBody BalanceDetailsTO balanceDetailsTO);

    /**
     * 删除用户余额明细
     */
    @DeleteMapping("/member/BalanceDetails/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}