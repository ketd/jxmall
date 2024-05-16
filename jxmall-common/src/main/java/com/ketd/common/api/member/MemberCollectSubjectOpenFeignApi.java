package com.ketd.common.api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberCollectSubjectTO;

@FeignClient(value = "cloud-gateway-service")
public interface MemberCollectSubjectOpenFeignApi {

    @PostMapping("/member/MemberCollectSubject/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberCollectSubjectTO> pageRequest);

    /**
     * 获取会员收藏的专题活动详细信息
     */
    @GetMapping(value = "/member/MemberCollectSubject/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员收藏的专题活动
     */
    @PostMapping("/member/MemberCollectSubject/save")
    public Result<?> add(@RequestBody MemberCollectSubjectTO memberCollectSubjectTO);

    /**
     * 修改会员收藏的专题活动
     */
    @PutMapping("/member/MemberCollectSubject/update")
    public Result<?> edit(@RequestBody MemberCollectSubjectTO memberCollectSubjectTO);

    /**
     * 删除会员收藏的专题活动
     */
    @DeleteMapping("/member/MemberCollectSubject/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}