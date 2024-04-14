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

import com.ketd.product.domain.SpuImages;
import com.ketd.product.service.ISpuImagesService;


/**
 * spu图片Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "spu图片Controller")
@RestController
@RequestMapping("/product/SpuImages")
public class SpuImagesController{

    @Autowired
    private ISpuImagesService spuImagesService;

    /**
     * 分页查询spu图片列表
     */
    @Operation(summary ="分页查询spu图片列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuImages> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SpuImages spuImages= pageRequest.getData();
        Page<SpuImages> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuImages> queryWrapper = new QueryWrapper<>(spuImages);

        IPage<SpuImages> spuImagesPage = spuImagesService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(spuImagesPage.getRecords(), spuImagesPage.getTotal());



    }


    /**
     * 导出spu图片列表
     */
    @Operation(summary = "导出spu图片列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] spuImagesIds)
    {
        List<SpuImages> list = spuImagesService.listByIds(Arrays.asList(spuImagesIds));
            spuImagesService.export(list, response);

    }

    /**
     * 获取spu图片详细信息
     */
    @Operation(summary = "获取spu图片详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(spuImagesService.selectSpuImagesById(id));
    }

    /**
     * 新增spu图片
     */
    @Operation(summary = "新增spu图片")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SpuImages spuImages)
    {
        return Result.ok(spuImagesService.insertSpuImages(spuImages));
    }

    /**
     * 修改spu图片
     */
    @Operation(summary = "修改spu图片")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SpuImages spuImages)
    {
        return Result.ok(spuImagesService.updateSpuImages(spuImages));
    }

    /**
     * 删除spu图片
     */
    @Operation(summary = "删除spu图片")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(spuImagesService.deleteSpuImagesByIds(ids));
    }
}
