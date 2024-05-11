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

import com.ketd.coupon.domain.CouponSpuCategoryRelation;
import com.ketd.coupon.service.ICouponSpuCategoryRelationService;


/**
 * 优惠券分类关联Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "优惠券分类关联Controller")
@RestController
@RequestMapping("/coupon/CouponSpuCategoryRelation")
public class CouponSpuCategoryRelationController{

    @Autowired
    private ICouponSpuCategoryRelationService couponSpuCategoryRelationService;

    /**
     * 分页查询优惠券分类关联列表
     */
    @Operation(summary ="分页查询优惠券分类关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CouponSpuCategoryRelation> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        CouponSpuCategoryRelation couponSpuCategoryRelation= pageRequest.getData();
        Page<CouponSpuCategoryRelation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CouponSpuCategoryRelation> queryWrapper = new QueryWrapper<>(couponSpuCategoryRelation);

        IPage<CouponSpuCategoryRelation> couponSpuCategoryRelationPage = couponSpuCategoryRelationService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(couponSpuCategoryRelationPage.getRecords(), couponSpuCategoryRelationPage.getTotal());



    }


    /**
     * 导出优惠券分类关联列表
     */
    @Operation(summary = "导出优惠券分类关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] couponSpuCategoryRelationIds)
    {
        List<CouponSpuCategoryRelation> list = couponSpuCategoryRelationService.listByIds(Arrays.asList(couponSpuCategoryRelationIds));
            couponSpuCategoryRelationService.export(list, response);

    }

    /**
     * 获取优惠券分类关联详细信息
     */
    @Operation(summary = "获取优惠券分类关联详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(couponSpuCategoryRelationService.selectCouponSpuCategoryRelationById(id));
    }

    /**
     * 新增优惠券分类关联
     */
    @Operation(summary = "新增优惠券分类关联")
    @PostMapping("/save")
    public Result<?> add(@RequestBody CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        return Result.ok(couponSpuCategoryRelationService.insertCouponSpuCategoryRelation(couponSpuCategoryRelation));
    }

    /**
     * 修改优惠券分类关联
     */
    @Operation(summary = "修改优惠券分类关联")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        return Result.ok(couponSpuCategoryRelationService.updateCouponSpuCategoryRelation(couponSpuCategoryRelation));
    }

    /**
     * 删除优惠券分类关联
     */
    @Operation(summary = "删除优惠券分类关联")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(couponSpuCategoryRelationService.deleteCouponSpuCategoryRelationByIds(ids));
    }
}
