package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberOpenFeignApi {

    @PostMapping("/member/Member/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberTO> pageRequest);

    /**
     * 获取会员详细信息
     */
    @GetMapping(value = "/member/Member/info")
    public Result<MemberTO> getInfo(@RequestParam("id") Long id);

    @GetMapping(value = "/member/Member/info/mobile")
    public Result<MemberTO> getInfoByMobile(@RequestParam("mobile") String mobile);

    @GetMapping(value = "/member/Member/info/email")
    public Result<MemberTO> getInfoByEmail(@RequestParam("email") String email);
    /**
     * 新增会员
     */
    @PostMapping(value ="/member/Member/save")
    public Result<MemberTO> add(@RequestBody MemberTO memberTO);

    /**
     * 修改会员
     */
    @PutMapping("/member/Member/update")
    public Result<?> edit(@RequestBody MemberTO memberTO);

    /**
     * 删除会员
     */
    @DeleteMapping("/member/Member/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}