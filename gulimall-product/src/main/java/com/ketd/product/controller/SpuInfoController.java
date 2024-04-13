package com.ketd.product.controller;




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

import com.ketd.common.BaseController;
import com.ketd.common.AjaxResult;
import com.ketd.common.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.SpuInfo;
import com.ketd.product.service.ISpuInfoService;


/**
 * spu信息Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "spu信息Controller")
@RestController
@RequestMapping("/product/SpuInfo")
public class SpuInfoController extends BaseController{

    @Autowired
    private ISpuInfoService spuInfoService;

    /**
     * 分页查询spu信息列表
     */
    @Operation(summary ="分页查询spu信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SpuInfo spuInfo= pageRequest.getData();
        Page<SpuInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>(spuInfo);

        IPage<SpuInfo> spuInfoPage = spuInfoService.page(page, queryWrapper);
        return getDataTable(spuInfoPage.getRecords(), spuInfoPage.getTotal());



    }


    /**
     * 导出spu信息列表
     */
    @Operation(summary = "导出spu信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] spuInfoIds)
    {
        List<SpuInfo> list = spuInfoService.listByIds(Arrays.asList(spuInfoIds));
            spuInfoService.export(list, response);

    }

    /**
     * 获取spu信息详细信息
     */
    @Operation(summary = "获取spu信息详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("id") Long id)
    {
        return success(spuInfoService.selectSpuInfoById(id));
    }

    /**
     * 新增spu信息
     */
    @Operation(summary = "新增spu信息")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody SpuInfo spuInfo)
    {
        return toAjax(spuInfoService.insertSpuInfo(spuInfo));
    }

    /**
     * 修改spu信息
     */
    @Operation(summary = "修改spu信息")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody SpuInfo spuInfo)
    {
        return toAjax(spuInfoService.updateSpuInfo(spuInfo));
    }

    /**
     * 删除spu信息
     */
    @Operation(summary = "删除spu信息")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(spuInfoService.deleteSpuInfoByIds(ids));
    }
}
