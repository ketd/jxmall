package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberLevelTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberLevelOpenFeignApi {

    @PostMapping("/member/MemberLevel/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberLevelTO> pageRequest);

    /**
     * 获取会员等级详细信息
     */
    @GetMapping(value = "/member/MemberLevel/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员等级
     */
    @PostMapping("/member/MemberLevel/save")
    public Result<?> add(@RequestBody MemberLevelTO memberLevelTO);

    /**
     * 修改会员等级
     */
    @PutMapping("/member/MemberLevel/update")
    public Result<?> edit(@RequestBody MemberLevelTO memberLevelTO);

    /**
     * 删除会员等级
     */
    @DeleteMapping("/member/MemberLevel/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}