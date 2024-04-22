package com.ketd.common.api.coupon;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.coupon.HomeSubjectTO;

@FeignClient(value = "cloud-gateway-service")
public interface HomeSubjectOpenFeignApi {

    @PostMapping("/coupon/HomeSubject/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeSubjectTO> pageRequest);

    /**
     * 获取首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详细信息
     */
    @GetMapping(value = "/coupon/HomeSubject/info")
    public Result<?> getInfo(@RequestParam("id") Long id);
    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @PostMapping("/coupon/HomeSubject/save")
    public Result<?> add(@RequestBody HomeSubjectTO homeSubjectTO);

    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @PutMapping("/coupon/HomeSubject/update")
    public Result<?> edit(@RequestBody HomeSubjectTO homeSubjectTO);

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @DeleteMapping("/coupon/HomeSubject/delete")
    public Result<?> remove(@RequestBody Long[] ids);

}