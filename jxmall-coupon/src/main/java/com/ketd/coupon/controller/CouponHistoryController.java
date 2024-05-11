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

import com.ketd.coupon.domain.CouponHistory;
import com.ketd.coupon.service.ICouponHistoryService;


/**
 * 优惠券领取历史记录Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "优惠券领取历史记录Controller")
@RestController
@RequestMapping("/coupon/CouponHistory")
public class CouponHistoryController{

    @Autowired
    private ICouponHistoryService couponHistoryService;

    /**
     * 分页查询优惠券领取历史记录列表
     */
    @Operation(summary ="分页查询优惠券领取历史记录列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponHistory> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        CouponHistory couponHistory= pageRequest.getData();
        Page<CouponHistory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CouponHistory> queryWrapper = new QueryWrapper<>(couponHistory);

        IPage<CouponHistory> couponHistoryPage = couponHistoryService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(couponHistoryPage.getRecords(), couponHistoryPage.getTotal());



    }


    /**
     * 导出优惠券领取历史记录列表
     */
    @Operation(summary = "导出优惠券领取历史记录列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] couponHistoryIds)
    {
        List<CouponHistory> list = couponHistoryService.listByIds(Arrays.asList(couponHistoryIds));
            couponHistoryService.export(list, response);

    }

    /**
     * 获取优惠券领取历史记录详细信息
     */
    @Operation(summary = "获取优惠券领取历史记录详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(couponHistoryService.selectCouponHistoryById(id));
    }

    /**
     * 新增优惠券领取历史记录
     */
    @Operation(summary = "新增优惠券领取历史记录")
    @PostMapping("/save")
    public Result<?> add(@RequestBody CouponHistory couponHistory)
    {
        return Result.ok(couponHistoryService.insertCouponHistory(couponHistory));
    }

    /**
     * 修改优惠券领取历史记录
     */
    @Operation(summary = "修改优惠券领取历史记录")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody CouponHistory couponHistory)
    {
        return Result.ok(couponHistoryService.updateCouponHistory(couponHistory));
    }

    /**
     * 删除优惠券领取历史记录
     */
    @Operation(summary = "删除优惠券领取历史记录")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(couponHistoryService.deleteCouponHistoryByIds(ids));
    }
}
