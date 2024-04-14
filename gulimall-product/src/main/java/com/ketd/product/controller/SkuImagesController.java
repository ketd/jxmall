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

import com.ketd.product.domain.SkuImages;
import com.ketd.product.service.ISkuImagesService;


/**
 * sku图片Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "sku图片Controller")
@RestController
@RequestMapping("/product/SkuImages")
public class SkuImagesController{

    @Autowired
    private ISkuImagesService skuImagesService;

    /**
     * 分页查询sku图片列表
     */
    @Operation(summary ="分页查询sku图片列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuImages> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SkuImages skuImages= pageRequest.getData();
        Page<SkuImages> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkuImages> queryWrapper = new QueryWrapper<>(skuImages);

        IPage<SkuImages> skuImagesPage = skuImagesService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(skuImagesPage.getRecords(), skuImagesPage.getTotal());



    }


    /**
     * 导出sku图片列表
     */
    @Operation(summary = "导出sku图片列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] skuImagesIds)
    {
        List<SkuImages> list = skuImagesService.listByIds(Arrays.asList(skuImagesIds));
            skuImagesService.export(list, response);

    }

    /**
     * 获取sku图片详细信息
     */
    @Operation(summary = "获取sku图片详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(skuImagesService.selectSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @Operation(summary = "新增sku图片")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SkuImages skuImages)
    {
        return Result.ok(skuImagesService.insertSkuImages(skuImages));
    }

    /**
     * 修改sku图片
     */
    @Operation(summary = "修改sku图片")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SkuImages skuImages)
    {
        return Result.ok(skuImagesService.updateSkuImages(skuImages));
    }

    /**
     * 删除sku图片
     */
    @Operation(summary = "删除sku图片")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(skuImagesService.deleteSkuImagesByIds(ids));
    }
}
