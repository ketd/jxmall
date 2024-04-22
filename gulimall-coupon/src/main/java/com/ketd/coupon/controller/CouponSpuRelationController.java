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

import com.ketd.coupon.domain.CouponSpuRelation;
import com.ketd.coupon.service.ICouponSpuRelationService;


/**
 * 优惠券与产品关联Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "优惠券与产品关联Controller")
@RestController
@RequestMapping("/coupon/CouponSpuRelation")
public class CouponSpuRelationController{

    @Autowired
    private ICouponSpuRelationService couponSpuRelationService;

    /**
     * 分页查询优惠券与产品关联列表
     */
    @Operation(summary ="分页查询优惠券与产品关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponSpuRelation> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        CouponSpuRelation couponSpuRelation= pageRequest.getData();
        Page<CouponSpuRelation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CouponSpuRelation> queryWrapper = new QueryWrapper<>(couponSpuRelation);

        IPage<CouponSpuRelation> couponSpuRelationPage = couponSpuRelationService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(couponSpuRelationPage.getRecords(), couponSpuRelationPage.getTotal());



    }


    /**
     * 导出优惠券与产品关联列表
     */
    @Operation(summary = "导出优惠券与产品关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] couponSpuRelationIds)
    {
        List<CouponSpuRelation> list = couponSpuRelationService.listByIds(Arrays.asList(couponSpuRelationIds));
            couponSpuRelationService.export(list, response);

    }

    /**
     * 获取优惠券与产品关联详细信息
     */
    @Operation(summary = "获取优惠券与产品关联详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(couponSpuRelationService.selectCouponSpuRelationById(id));
    }

    /**
     * 新增优惠券与产品关联
     */
    @Operation(summary = "新增优惠券与产品关联")
    @PostMapping("/save")
    public Result<?> add(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return Result.ok(couponSpuRelationService.insertCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 修改优惠券与产品关联
     */
    @Operation(summary = "修改优惠券与产品关联")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return Result.ok(couponSpuRelationService.updateCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 删除优惠券与产品关联
     */
    @Operation(summary = "删除优惠券与产品关联")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(couponSpuRelationService.deleteCouponSpuRelationByIds(ids));
    }
}
