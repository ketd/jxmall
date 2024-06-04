package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberLoginLogTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberLoginLogOpenFeignApi {

    @PostMapping("/member/MemberLoginLog/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberLoginLogTO> pageRequest);

    /**
     * 获取会员登录记录详细信息
     */
    @GetMapping(value = "/member/MemberLoginLog/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员登录记录
     */
    @PostMapping("/member/MemberLoginLog/save")
    public Result<?> add(@RequestBody MemberLoginLogTO memberLoginLogTO);

    /**
     * 修改会员登录记录
     */
    @PutMapping("/member/MemberLoginLog/update")
    public Result<?> edit(@RequestBody MemberLoginLogTO memberLoginLogTO);

    /**
     * 删除会员登录记录
     */
    @DeleteMapping("/member/MemberLoginLog/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}