package com.ketd.coupon.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.coupon.domain.Coupon;
import com.ketd.coupon.service.ICouponService;


/**
 * 优惠券信息Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "优惠券信息Controller")
@RestController
@RequestMapping("/coupon/Coupon")
public class CouponController{

    @Autowired
    private ICouponService couponService;




    /**
     * 分页查询优惠券信息列表
     */
    @Operation(summary ="分页查询优惠券信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Coupon> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Coupon coupon= pageRequest.getData();
        Page<Coupon> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>(coupon);

        IPage<Coupon> couponPage = couponService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(couponPage.getRecords(), couponPage.getTotal());



    }


    /**
     * 导出优惠券信息列表
     */
    @Operation(summary = "导出优惠券信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] couponIds)
    {
        List<Coupon> list = couponService.listByIds(Arrays.asList(couponIds));
            couponService.export(list, response);

    }

    /**
     * 获取优惠券信息详细信息
     */
    @Operation(summary = "获取优惠券信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(couponService.selectCouponById(id));
    }

    /**
     * 新增优惠券信息
     */
    @Operation(summary = "新增优惠券信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Coupon coupon)
    {
        return Result.ok(couponService.insertCoupon(coupon));
    }

    /**
     * 修改优惠券信息
     */
    @Operation(summary = "修改优惠券信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Coupon coupon)
    {
        return Result.ok(couponService.updateCoupon(coupon));
    }

    /**
     * 删除优惠券信息
     */
    @Operation(summary = "删除优惠券信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(couponService.deleteCouponByIds(ids));
    }
}
