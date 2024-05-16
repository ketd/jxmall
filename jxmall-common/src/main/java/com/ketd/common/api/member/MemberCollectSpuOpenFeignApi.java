package com.ketd.common.api.member;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.member.MemberCollectSpuTO;

@FeignClient(value = "cloud-gateway-service")
public interface MemberCollectSpuOpenFeignApi {

    @PostMapping("/member/MemberCollectSpu/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberCollectSpuTO> pageRequest);

    /**
     * 获取会员收藏的商品详细信息
     */
    @GetMapping(value = "/member/MemberCollectSpu/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增会员收藏的商品
     */
    @PostMapping("/member/MemberCollectSpu/save")
    public Result<?> add(@RequestBody MemberCollectSpuTO memberCollectSpuTO);

    /**
     * 修改会员收藏的商品
     */
    @PutMapping("/member/MemberCollectSpu/update")
    public Result<?> edit(@RequestBody MemberCollectSpuTO memberCollectSpuTO);

    /**
     * 删除会员收藏的商品
     */
    @DeleteMapping("/member/MemberCollectSpu/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}