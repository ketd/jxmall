package com.ketd.member.controller;




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

import com.ketd.member.domain.GrowthChangeHistory;
import com.ketd.member.service.IGrowthChangeHistoryService;


/**
 * 成长值变化历史记录Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "成长值变化历史记录Controller")
@RestController
@RequestMapping("/member/GrowthChangeHistory")
public class GrowthChangeHistoryController{

    @Autowired
    private IGrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 分页查询成长值变化历史记录列表
     */
    @Operation(summary ="分页查询成长值变化历史记录列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<GrowthChangeHistory> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        GrowthChangeHistory growthChangeHistory= pageRequest.getData();
        Page<GrowthChangeHistory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<GrowthChangeHistory> queryWrapper = new QueryWrapper<>(growthChangeHistory);

        IPage<GrowthChangeHistory> growthChangeHistoryPage = growthChangeHistoryService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(growthChangeHistoryPage.getRecords(), growthChangeHistoryPage.getTotal());



    }


    /**
     * 导出成长值变化历史记录列表
     */
    @Operation(summary = "导出成长值变化历史记录列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] growthChangeHistoryIds)
    {
        List<GrowthChangeHistory> list = growthChangeHistoryService.listByIds(Arrays.asList(growthChangeHistoryIds));
            growthChangeHistoryService.export(list, response);

    }

    /**
     * 获取成长值变化历史记录详细信息
     */
    @Operation(summary = "获取成长值变化历史记录详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(growthChangeHistoryService.selectGrowthChangeHistoryById(id));
    }

    /**
     * 新增成长值变化历史记录
     */
    @Operation(summary = "新增成长值变化历史记录")
    @PostMapping("/save")
    public Result<?> add(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return Result.ok(growthChangeHistoryService.insertGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 修改成长值变化历史记录
     */
    @Operation(summary = "修改成长值变化历史记录")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return Result.ok(growthChangeHistoryService.updateGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 删除成长值变化历史记录
     */
    @Operation(summary = "删除成长值变化历史记录")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(growthChangeHistoryService.deleteGrowthChangeHistoryByIds(ids));
    }
}
