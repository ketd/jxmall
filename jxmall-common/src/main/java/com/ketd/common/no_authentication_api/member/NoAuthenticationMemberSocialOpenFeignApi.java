package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberSocialTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberSocialOpenFeignApi {

    @PostMapping("/member/MemberSocial/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberSocialTO> pageRequest);

    /**
     * 获取单点登录用户信息关联详细信息
     */
    @GetMapping(value = "/member/MemberSocial/info")
    public Result<?> getInfo(@RequestParam("memberId") Long memberId);
    /**
     * 新增单点登录用户信息关联
     */
    @PostMapping("/member/MemberSocial/save")
    public Result<?> add(@RequestBody MemberSocialTO memberSocialTO);

    /**
     * 修改单点登录用户信息关联
     */
    @PutMapping("/member/MemberSocial/update")
    public Result<?> edit(@RequestBody MemberSocialTO memberSocialTO);

    /**
     * 删除单点登录用户信息关联
     */
    @DeleteMapping("/member/MemberSocial/delete")
    public Result<?> remove(@RequestBody Long[] memberIds);

}