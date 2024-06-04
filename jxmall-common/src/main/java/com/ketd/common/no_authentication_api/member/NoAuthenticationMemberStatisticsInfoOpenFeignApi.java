package com.ketd.common.no_authentication_api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberStatisticsInfoTO;

@FeignClient(value = "cloud-no-authentication-gateway-service")
public interface NoAuthenticationMemberStatisticsInfoOpenFeignApi {

    @PostMapping("/member/MemberStatisticsInfo/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberStatisticsInfoTO> pageRequest);

    /**
     * 获取会员统计信息详细信息
     */
    @GetMapping(value = "/member/MemberStatisticsInfo/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员统计信息
     */
    @PostMapping("/member/MemberStatisticsInfo/save")
    public Result<?> add(@RequestBody MemberStatisticsInfoTO memberStatisticsInfoTO);

    /**
     * 修改会员统计信息
     */
    @PutMapping("/member/MemberStatisticsInfo/update")
    public Result<?> edit(@RequestBody MemberStatisticsInfoTO memberStatisticsInfoTO);

    /**
     * 删除会员统计信息
     */
    @DeleteMapping("/member/MemberStatisticsInfo/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}