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

import com.ketd.ware.domain.WareInfo;
import com.ketd.ware.service.IWareInfoService;


/**
 * 仓库信息Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "仓库信息Controller")
@RestController
@RequestMapping("/ware/WareInfo")
public class WareInfoController{

    @Autowired
    private IWareInfoService wareInfoService;

    /**
     * 分页查询仓库信息列表
     */
    @Operation(summary ="分页查询仓库信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        WareInfo wareInfo= pageRequest.getData();
        Page<WareInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<WareInfo> queryWrapper = new QueryWrapper<>(wareInfo);

        IPage<WareInfo> wareInfoPage = wareInfoService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(wareInfoPage.getRecords(), wareInfoPage.getTotal());



    }


    /**
     * 导出仓库信息列表
     */
    @Operation(summary = "导出仓库信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] wareInfoIds)
    {
        List<WareInfo> list = wareInfoService.listByIds(Arrays.asList(wareInfoIds));
            wareInfoService.export(list, response);

    }

    /**
     * 获取仓库信息详细信息
     */
    @Operation(summary = "获取仓库信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(wareInfoService.selectWareInfoById(id));
    }

    /**
     * 新增仓库信息
     */
    @Operation(summary = "新增仓库信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody WareInfo wareInfo)
    {
        return Result.ok(wareInfoService.insertWareInfo(wareInfo));
    }

    /**
     * 修改仓库信息
     */
    @Operation(summary = "修改仓库信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody WareInfo wareInfo)
    {
        return Result.ok(wareInfoService.updateWareInfo(wareInfo));
    }

    /**
     * 删除仓库信息
     */
    @Operation(summary = "删除仓库信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(wareInfoService.deleteWareInfoByIds(ids));
    }
}
