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

import com.ketd.coupon.domain.SeckillPromotion;
import com.ketd.coupon.service.ISeckillPromotionService;


/**
 * 秒杀活动Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "秒杀活动Controller")
@RestController
@RequestMapping("/coupon/SeckillPromotion")
public class SeckillPromotionController{

    @Autowired
    private ISeckillPromotionService seckillPromotionService;

    /**
     * 分页查询秒杀活动列表
     */
    @Operation(summary ="分页查询秒杀活动列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillPromotion> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SeckillPromotion seckillPromotion= pageRequest.getData();
        Page<SeckillPromotion> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SeckillPromotion> queryWrapper = new QueryWrapper<>(seckillPromotion);

        IPage<SeckillPromotion> seckillPromotionPage = seckillPromotionService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(seckillPromotionPage.getRecords(), seckillPromotionPage.getTotal());



    }


    /**
     * 导出秒杀活动列表
     */
    @Operation(summary = "导出秒杀活动列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] seckillPromotionIds)
    {
        List<SeckillPromotion> list = seckillPromotionService.listByIds(Arrays.asList(seckillPromotionIds));
            seckillPromotionService.export(list, response);

    }

    /**
     * 获取秒杀活动详细信息
     */
    @Operation(summary = "获取秒杀活动详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(seckillPromotionService.selectSeckillPromotionById(id));
    }

    /**
     * 新增秒杀活动
     */
    @Operation(summary = "新增秒杀活动")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SeckillPromotion seckillPromotion)
    {
        return Result.ok(seckillPromotionService.insertSeckillPromotion(seckillPromotion));
    }

    /**
     * 修改秒杀活动
     */
    @Operation(summary = "修改秒杀活动")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SeckillPromotion seckillPromotion)
    {
        return Result.ok(seckillPromotionService.updateSeckillPromotion(seckillPromotion));
    }

    /**
     * 删除秒杀活动
     */
    @Operation(summary = "删除秒杀活动")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(seckillPromotionService.deleteSeckillPromotionByIds(ids));
    }
}
