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

import com.ketd.coupon.domain.HomeAdv;
import com.ketd.coupon.service.IHomeAdvService;


/**
 * 首页轮播广告Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "首页轮播广告Controller")
@RestController
@RequestMapping("/coupon/HomeAdv")
public class HomeAdvController{

    @Autowired
    private IHomeAdvService homeAdvService;

    /**
     * 分页查询首页轮播广告列表
     */
    @Operation(summary ="分页查询首页轮播广告列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeAdv> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        HomeAdv homeAdv= pageRequest.getData();
        Page<HomeAdv> page = new Page<>(pageNum, pageSize);
        QueryWrapper<HomeAdv> queryWrapper = new QueryWrapper<>(homeAdv);

        IPage<HomeAdv> homeAdvPage = homeAdvService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(homeAdvPage.getRecords(), homeAdvPage.getTotal());



    }


    /**
     * 导出首页轮播广告列表
     */
    @Operation(summary = "导出首页轮播广告列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] homeAdvIds)
    {
        List<HomeAdv> list = homeAdvService.listByIds(Arrays.asList(homeAdvIds));
            homeAdvService.export(list, response);

    }

    /**
     * 获取首页轮播广告详细信息
     */
    @Operation(summary = "获取首页轮播广告详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(homeAdvService.selectHomeAdvById(id));
    }

    /**
     * 新增首页轮播广告
     */
    @Operation(summary = "新增首页轮播广告")
    @PostMapping("/save")
    public Result<?> add(@RequestBody HomeAdv homeAdv)
    {
        return Result.ok(homeAdvService.insertHomeAdv(homeAdv));
    }

    /**
     * 修改首页轮播广告
     */
    @Operation(summary = "修改首页轮播广告")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody HomeAdv homeAdv)
    {
        return Result.ok(homeAdvService.updateHomeAdv(homeAdv));
    }

    /**
     * 删除首页轮播广告
     */
    @Operation(summary = "删除首页轮播广告")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(homeAdvService.deleteHomeAdvByIds(ids));
    }
}
