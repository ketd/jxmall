package com.ketd.product.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ketd.product.vo.SpuSaveVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.SpuInfo;
import com.ketd.product.service.ISpuInfoService;


/**
 * spu信息Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "spu信息Controller")
@RestController
@RequestMapping("/product/SpuInfo")
public class SpuInfoController{

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
        return TableDataInfo.getDataTable(spuInfoPage.getRecords(), spuInfoPage.getTotal());



    }

    /**
     * 新增spu信息
     */
    @Operation(summary = "新增spu信息")
    @PostMapping("/save/info")
    public Result<?>  save(@RequestBody SpuSaveVo spuInfoVo)
    {
        return spuInfoService.saveSpuInfo(spuInfoVo);
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
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(spuInfoService.selectSpuInfoById(id));
    }

    /**
     * 新增spu信息
     */
    @Operation(summary = "保存spu信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SpuInfo spuInfo)
    {
        return Result.ok(spuInfoService.insertSpuInfo(spuInfo));
    }

    /**
     * 修改spu信息
     */
    @Operation(summary = "修改spu信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SpuInfo spuInfo)
    {
        return Result.ok(spuInfoService.updateSpuInfo(spuInfo));
    }

    /**
     * 删除spu信息
     */
    @Operation(summary = "删除spu信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(spuInfoService.deleteSpuInfoByIds(ids));
    }

    @PostMapping
    public Result<?> up(@RequestBody Long[] ids)
    {
       return spuInfoService.up(ids);
    }



}
