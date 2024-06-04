package com.ketd.common.api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.BalanceTO;

@FeignClient(value = "cloud-gateway-service")
public interface BalanceOpenFeignApi {

    @PostMapping("/member/Balance/list/page")
    public TableDataInfo list(@RequestBody PageRequest<BalanceTO> pageRequest);

    /**
     * 获取用户余额表详细信息
     */
    @GetMapping(value = "/member/Balance/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增用户余额表
     */
    @PostMapping("/member/Balance/save")
    public Result<?> add(@RequestBody BalanceTO balanceTO);

    /**
     * 修改用户余额表
     */
    @PutMapping("/member/Balance/update")
    public Result<?> edit(@RequestBody BalanceTO balanceTO);

    /**
     * 删除用户余额表
     */
    @DeleteMapping("/member/Balance/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}