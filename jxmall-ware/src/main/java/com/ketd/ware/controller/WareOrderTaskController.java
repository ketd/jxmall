package com.ketd.ware.controller;




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

import com.ketd.ware.domain.WareOrderTask;
import com.ketd.ware.service.IWareOrderTaskService;


/**
 * 库存工作单Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "库存工作单Controller")
@RestController
@RequestMapping("/ware/WareOrderTask")
public class WareOrderTaskController{

    @Autowired
    private IWareOrderTaskService wareOrderTaskService;

    /**
     * 分页查询库存工作单列表
     */
    @Operation(summary ="分页查询库存工作单列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareOrderTask> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        WareOrderTask wareOrderTask= pageRequest.getData();
        Page<WareOrderTask> page = new Page<>(pageNum, pageSize);
        QueryWrapper<WareOrderTask> queryWrapper = new QueryWrapper<>(wareOrderTask);

        IPage<WareOrderTask> wareOrderTaskPage = wareOrderTaskService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(wareOrderTaskPage.getRecords(), wareOrderTaskPage.getTotal());



    }


    /**
     * 导出库存工作单列表
     */
    @Operation(summary = "导出库存工作单列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] wareOrderTaskIds)
    {
        List<WareOrderTask> list = wareOrderTaskService.listByIds(Arrays.asList(wareOrderTaskIds));
            wareOrderTaskService.export(list, response);

    }

    /**
     * 获取库存工作单详细信息
     */
    @Operation(summary = "获取库存工作单详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(wareOrderTaskService.selectWareOrderTaskById(id));
    }

    /**
     * 新增库存工作单
     */
    @Operation(summary = "新增库存工作单")
    @PostMapping("/save")
    public Result<?> add(@RequestBody WareOrderTask wareOrderTask)
    {
        return Result.ok(wareOrderTaskService.insertWareOrderTask(wareOrderTask));
    }

    /**
     * 修改库存工作单
     */
    @Operation(summary = "修改库存工作单")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody WareOrderTask wareOrderTask)
    {
        return Result.ok(wareOrderTaskService.updateWareOrderTask(wareOrderTask));
    }

    /**
     * 删除库存工作单
     */
    @Operation(summary = "删除库存工作单")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(wareOrderTaskService.deleteWareOrderTaskByIds(ids));
    }
}
