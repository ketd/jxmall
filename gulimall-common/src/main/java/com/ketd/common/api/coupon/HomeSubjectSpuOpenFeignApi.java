package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.HomeSubjectSpuTO;

@FeignClient(value = "cloud-gateway-service")
public interface HomeSubjectSpuOpenFeignApi {

    @PostMapping("/coupon/HomeSubjectSpu/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeSubjectSpuTO> pageRequest);

    /**
     * 获取专题商品详细信息
     */
    @GetMapping(value = "/coupon/HomeSubjectSpu/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增专题商品
     */
    @PostMapping("/coupon/HomeSubjectSpu/save")
    public Result<?> add(@RequestBody HomeSubjectSpuTO homeSubjectSpuTO);

    /**
     * 修改专题商品
     */
    @PutMapping("/coupon/HomeSubjectSpu/update")
    public Result<?> edit(@RequestBody HomeSubjectSpuTO homeSubjectSpuTO);

    /**
     * 删除专题商品
     */
    @DeleteMapping("/coupon/HomeSubjectSpu/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}