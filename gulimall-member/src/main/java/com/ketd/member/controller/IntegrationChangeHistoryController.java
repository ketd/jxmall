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

import com.ketd.member.domain.IntegrationChangeHistory;
import com.ketd.member.service.IIntegrationChangeHistoryService;


/**
 * 积分变化历史记录Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "积分变化历史记录Controller")
@RestController
@RequestMapping("/member/IntegrationChangeHistory")
public class IntegrationChangeHistoryController{

    @Autowired
    private IIntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 分页查询积分变化历史记录列表
     */
    @Operation(summary ="分页查询积分变化历史记录列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<IntegrationChangeHistory> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        IntegrationChangeHistory integrationChangeHistory= pageRequest.getData();
        Page<IntegrationChangeHistory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<IntegrationChangeHistory> queryWrapper = new QueryWrapper<>(integrationChangeHistory);

        IPage<IntegrationChangeHistory> integrationChangeHistoryPage = integrationChangeHistoryService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(integrationChangeHistoryPage.getRecords(), integrationChangeHistoryPage.getTotal());



    }


    /**
     * 导出积分变化历史记录列表
     */
    @Operation(summary = "导出积分变化历史记录列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] integrationChangeHistoryIds)
    {
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.listByIds(Arrays.asList(integrationChangeHistoryIds));
            integrationChangeHistoryService.export(list, response);

    }

    /**
     * 获取积分变化历史记录详细信息
     */
    @Operation(summary = "获取积分变化历史记录详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(integrationChangeHistoryService.selectIntegrationChangeHistoryById(id));
    }

    /**
     * 新增积分变化历史记录
     */
    @Operation(summary = "新增积分变化历史记录")
    @PostMapping("/save")
    public Result<?> add(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return Result.ok(integrationChangeHistoryService.insertIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 修改积分变化历史记录
     */
    @Operation(summary = "修改积分变化历史记录")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return Result.ok(integrationChangeHistoryService.updateIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 删除积分变化历史记录
     */
    @Operation(summary = "删除积分变化历史记录")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(integrationChangeHistoryService.deleteIntegrationChangeHistoryByIds(ids));
    }
}
