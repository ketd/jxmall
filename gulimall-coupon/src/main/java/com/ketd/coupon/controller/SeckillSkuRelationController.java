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

import com.ketd.coupon.domain.SeckillSkuRelation;
import com.ketd.coupon.service.ISeckillSkuRelationService;


/**
 * 秒杀活动商品关联Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "秒杀活动商品关联Controller")
@RestController
@RequestMapping("/coupon/SeckillSkuRelation")
public class SeckillSkuRelationController{

    @Autowired
    private ISeckillSkuRelationService seckillSkuRelationService;

    /**
     * 分页查询秒杀活动商品关联列表
     */
    @Operation(summary ="分页查询秒杀活动商品关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSkuRelation> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SeckillSkuRelation seckillSkuRelation= pageRequest.getData();
        Page<SeckillSkuRelation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SeckillSkuRelation> queryWrapper = new QueryWrapper<>(seckillSkuRelation);

        IPage<SeckillSkuRelation> seckillSkuRelationPage = seckillSkuRelationService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(seckillSkuRelationPage.getRecords(), seckillSkuRelationPage.getTotal());



    }


    /**
     * 导出秒杀活动商品关联列表
     */
    @Operation(summary = "导出秒杀活动商品关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] seckillSkuRelationIds)
    {
        List<SeckillSkuRelation> list = seckillSkuRelationService.listByIds(Arrays.asList(seckillSkuRelationIds));
            seckillSkuRelationService.export(list, response);

    }

    /**
     * 获取秒杀活动商品关联详细信息
     */
    @Operation(summary = "获取秒杀活动商品关联详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(seckillSkuRelationService.selectSeckillSkuRelationById(id));
    }

    /**
     * 新增秒杀活动商品关联
     */
    @Operation(summary = "新增秒杀活动商品关联")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return Result.ok(seckillSkuRelationService.insertSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 修改秒杀活动商品关联
     */
    @Operation(summary = "修改秒杀活动商品关联")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return Result.ok(seckillSkuRelationService.updateSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 删除秒杀活动商品关联
     */
    @Operation(summary = "删除秒杀活动商品关联")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(seckillSkuRelationService.deleteSeckillSkuRelationByIds(ids));
    }
}
