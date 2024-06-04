package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberReceiveAddressTO;

import java.util.List;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberReceiveAddressOpenFeignApi {

    @PostMapping("/member/MemberReceiveAddress/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberReceiveAddressTO> pageRequest);

    @PostMapping("/member/MemberReceiveAddress/list/getMemberAddressById")
    public Result<List<MemberReceiveAddressTO>> getMemberAddressById(@RequestParam("id")  Long id);
    /**
     * 获取会员收货地址详细信息
     */
    @GetMapping(value = "/member/MemberReceiveAddress/info")
    public Result<MemberReceiveAddressTO> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员收货地址
     */
    @PostMapping("/member/MemberReceiveAddress/save")
    public Result<?> add(@RequestBody MemberReceiveAddressTO memberReceiveAddressTO);

    /**
     * 修改会员收货地址
     */
    @PutMapping("/member/MemberReceiveAddress/update")
    public Result<?> edit(@RequestBody MemberReceiveAddressTO memberReceiveAddressTO);

    /**
     * 删除会员收货地址
     */
    @DeleteMapping("/member/MemberReceiveAddress/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}