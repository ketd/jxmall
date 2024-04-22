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

import com.ketd.ware.domain.WareOrderTaskDetail;
import com.ketd.ware.service.IWareOrderTaskDetailService;


/**
 * 库存工作单Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "库存工作单Controller")
@RestController
@RequestMapping("/ware/WareOrderTaskDetail")
public class WareOrderTaskDetailController{

    @Autowired
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 分页查询库存工作单列表
     */
    @Operation(summary ="分页查询库存工作单列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareOrderTaskDetail> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        WareOrderTaskDetail wareOrderTaskDetail= pageRequest.getData();
        Page<WareOrderTaskDetail> page = new Page<>(pageNum, pageSize);
        QueryWrapper<WareOrderTaskDetail> queryWrapper = new QueryWrapper<>(wareOrderTaskDetail);

        IPage<WareOrderTaskDetail> wareOrderTaskDetailPage = wareOrderTaskDetailService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(wareOrderTaskDetailPage.getRecords(), wareOrderTaskDetailPage.getTotal());



    }


    /**
     * 导出库存工作单列表
     */
    @Operation(summary = "导出库存工作单列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] wareOrderTaskDetailIds)
    {
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.listByIds(Arrays.asList(wareOrderTaskDetailIds));
            wareOrderTaskDetailService.export(list, response);

    }

    /**
     * 获取库存工作单详细信息
     */
    @Operation(summary = "获取库存工作单详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(wareOrderTaskDetailService.selectWareOrderTaskDetailById(id));
    }

    /**
     * 新增库存工作单
     */
    @Operation(summary = "新增库存工作单")
    @PostMapping("/save")
    public Result<?> add(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return Result.ok(wareOrderTaskDetailService.insertWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 修改库存工作单
     */
    @Operation(summary = "修改库存工作单")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return Result.ok(wareOrderTaskDetailService.updateWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 删除库存工作单
     */
    @Operation(summary = "删除库存工作单")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(wareOrderTaskDetailService.deleteWareOrderTaskDetailByIds(ids));
    }
}
