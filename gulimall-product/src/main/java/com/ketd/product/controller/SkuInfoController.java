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


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.SkuInfo;
import com.ketd.product.service.ISkuInfoService;


/**
 * sku信息Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "sku信息Controller")
@RestController
@RequestMapping("/product/SkuInfo")
public class SkuInfoController{

    @Autowired
    private ISkuInfoService skuInfoService;

    /**
     * 分页查询sku信息列表
     */
    @Operation(summary ="分页查询sku信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SkuInfo skuInfo= pageRequest.getData();
        Page<SkuInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>(skuInfo);

        IPage<SkuInfo> skuInfoPage = skuInfoService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(skuInfoPage.getRecords(), skuInfoPage.getTotal());



    }


    /**
     * 导出sku信息列表
     */
    @Operation(summary = "导出sku信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] skuInfoIds)
    {
        List<SkuInfo> list = skuInfoService.listByIds(Arrays.asList(skuInfoIds));
            skuInfoService.export(list, response);

    }

    /**
     * 获取sku信息详细信息
     */
    @Operation(summary = "获取sku信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("skuId") Long skuId)
    {
        return Result.ok(skuInfoService.selectSkuInfoBySkuId(skuId));
    }

    /**
     * 新增sku信息
     */
    @Operation(summary = "新增sku信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SkuInfo skuInfo)
    {
        return Result.ok(skuInfoService.insertSkuInfo(skuInfo));
    }

    /**
     * 修改sku信息
     */
    @Operation(summary = "修改sku信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SkuInfo skuInfo)
    {
        return Result.ok(skuInfoService.updateSkuInfo(skuInfo));
    }

    /**
     * 删除sku信息
     */
    @Operation(summary = "删除sku信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] skuIds)
    {
        return Result.ok(skuInfoService.deleteSkuInfoBySkuIds(skuIds));
    }
}
