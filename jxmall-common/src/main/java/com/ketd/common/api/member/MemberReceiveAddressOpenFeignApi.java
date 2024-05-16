package com.ketd.common.api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberReceiveAddressTO;

@FeignClient(value = "cloud-gateway-service")
public interface MemberReceiveAddressOpenFeignApi {

    @PostMapping("/member/MemberReceiveAddress/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberReceiveAddressTO> pageRequest);

    /**
     * 获取会员收货地址详细信息
     */
    @GetMapping(value = "/member/MemberReceiveAddress/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
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