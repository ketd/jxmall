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

import com.ketd.product.domain.CategoryBrandRelation;
import com.ketd.product.service.ICategoryBrandRelationService;


/**
 * 品牌分类关联Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "品牌分类关联Controller")
@RestController
@RequestMapping("/product/CategoryBrandRelation")
public class CategoryBrandRelationController{

    @Autowired
    private ICategoryBrandRelationService categoryBrandRelationService;

    /**
     * 分页查询品牌分类关联列表
     */
    @Operation(summary ="分页查询品牌分类关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CategoryBrandRelation> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        CategoryBrandRelation categoryBrandRelation= pageRequest.getData();
        Page<CategoryBrandRelation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CategoryBrandRelation> queryWrapper = new QueryWrapper<>(categoryBrandRelation);

        IPage<CategoryBrandRelation> categoryBrandRelationPage = categoryBrandRelationService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(categoryBrandRelationPage.getRecords(), categoryBrandRelationPage.getTotal());

    }

    @Operation(summary ="询品牌分类关联列表")
    @GetMapping("/list/brands")
    public Result<?>  selectBrandsByCatId(@RequestParam(value = "catId") Long catId)
    {

        return categoryBrandRelationService.selectBrandsByCatId(catId);

    }


    /**
     * 导出品牌分类关联列表
     */
    @Operation(summary = "导出品牌分类关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] categoryBrandRelationIds)
    {
        List<CategoryBrandRelation> list = categoryBrandRelationService.listByIds(Arrays.asList(categoryBrandRelationIds));
            categoryBrandRelationService.export(list, response);
    }

    /**
     * 获取品牌分类关联详细信息
     */
    @Operation(summary = "获取品牌分类关联详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(categoryBrandRelationService.selectCategoryBrandRelationById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @Operation(summary = "新增品牌分类关联")
    @PostMapping("/save")
    public Result<?> add(@RequestBody CategoryBrandRelation categoryBrandRelation)
    {
        return categoryBrandRelationService.insertCategoryBrandRelation(categoryBrandRelation);
    }

    /**
     * 修改品牌分类关联
     */
    @Operation(summary = "修改品牌分类关联")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody CategoryBrandRelation categoryBrandRelation)
    {
        return Result.ok(categoryBrandRelationService.updateCategoryBrandRelation(categoryBrandRelation));
    }

    /**
     * 删除品牌分类关联
     */
    @Operation(summary = "删除品牌分类关联")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(categoryBrandRelationService.deleteCategoryBrandRelationByIds(ids));
    }
}
