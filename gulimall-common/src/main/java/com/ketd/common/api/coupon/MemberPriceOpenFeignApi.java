package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.MemberPriceTO;

@FeignClient(value = "cloud-gateway-service")
public interface MemberPriceOpenFeignApi {

    @PostMapping("/coupon/MemberPrice/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberPriceTO> pageRequest);

    /**
     * 获取商品会员价格详细信息
     */
    @GetMapping(value = "/coupon/MemberPrice/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增商品会员价格
     */
    @PostMapping("/coupon/MemberPrice/save")
    public Result<?> add(@RequestBody MemberPriceTO memberPriceTO);

    /**
     * 修改商品会员价格
     */
    @PutMapping("/coupon/MemberPrice/update")
    public Result<?> edit(@RequestBody MemberPriceTO memberPriceTO);

    /**
     * 删除商品会员价格
     */
    @DeleteMapping("/coupon/MemberPrice/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}