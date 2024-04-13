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

import com.ketd.product.domain.SpuInfoDesc;
import com.ketd.product.service.ISpuInfoDescService;


/**
 * spu信息介绍Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "spu信息介绍Controller")
@RestController
@RequestMapping("/product/SpuInfoDesc")
public class SpuInfoDescController extends BaseController{

    @Autowired
    private ISpuInfoDescService spuInfoDescService;

    /**
     * 分页查询spu信息介绍列表
     */
    @Operation(summary ="分页查询spu信息介绍列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuInfoDesc> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SpuInfoDesc spuInfoDesc= pageRequest.getData();
        Page<SpuInfoDesc> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuInfoDesc> queryWrapper = new QueryWrapper<>(spuInfoDesc);

        IPage<SpuInfoDesc> spuInfoDescPage = spuInfoDescService.page(page, queryWrapper);
        return getDataTable(spuInfoDescPage.getRecords(), spuInfoDescPage.getTotal());



    }


    /**
     * 导出spu信息介绍列表
     */
    @Operation(summary = "导出spu信息介绍列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] spuInfoDescIds)
    {
        List<SpuInfoDesc> list = spuInfoDescService.listByIds(Arrays.asList(spuInfoDescIds));
            spuInfoDescService.export(list, response);

    }

    /**
     * 获取spu信息介绍详细信息
     */
    @Operation(summary = "获取spu信息介绍详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("spuId") Long spuId)
    {
        return success(spuInfoDescService.selectSpuInfoDescBySpuId(spuId));
    }

    /**
     * 新增spu信息介绍
     */
    @Operation(summary = "新增spu信息介绍")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody SpuInfoDesc spuInfoDesc)
    {
        return toAjax(spuInfoDescService.insertSpuInfoDesc(spuInfoDesc));
    }

    /**
     * 修改spu信息介绍
     */
    @Operation(summary = "修改spu信息介绍")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody SpuInfoDesc spuInfoDesc)
    {
        return toAjax(spuInfoDescService.updateSpuInfoDesc(spuInfoDesc));
    }

    /**
     * 删除spu信息介绍
     */
    @Operation(summary = "删除spu信息介绍")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] spuIds)
    {
        return toAjax(spuInfoDescService.deleteSpuInfoDescBySpuIds(spuIds));
    }
}
