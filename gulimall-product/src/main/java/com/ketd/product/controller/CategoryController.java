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

import com.ketd.product.domain.Category;
import com.ketd.product.service.ICategoryService;


/**
 * 商品三级分类Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "商品三级分类Controller")
@RestController
@RequestMapping("/product/Category")
public class CategoryController extends BaseController{

    @Autowired
    private ICategoryService categoryService;

    /**
     * 分页查询商品三级分类列表
     */
    @Operation(summary ="分页查询商品三级分类列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Category> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Category category= pageRequest.getData();
        Page<Category> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>(category);

        IPage<Category> categoryPage = categoryService.page(page, queryWrapper);
        return getDataTable(categoryPage.getRecords(), categoryPage.getTotal());



    }

    /**
     * 树形结构查询所有分类以及子分类
     */
    @Operation(summary ="树形结构查询所有分类以及子分类")
    @GetMapping("/list/tree")
    public AjaxResult list(){

        List<Category> categoryEntityList= categoryService.listWithTree();

        return success(categoryEntityList);
    }


    /**
     * 导出商品三级分类列表
     */
    @Operation(summary = "导出商品三级分类列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] categoryIds)
    {
        List<Category> list = categoryService.listByIds(Arrays.asList(categoryIds));
            categoryService.export(list, response);

    }

    /**
     * 获取商品三级分类详细信息
     */
    @Operation(summary = "获取商品三级分类详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("catId") Long catId)
    {
        return success(categoryService.selectCategoryByCatId(catId));
    }

    /**
     * 新增商品三级分类
     */
    @Operation(summary = "新增商品三级分类")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品三级分类
     */
    @Operation(summary = "修改商品三级分类")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品三级分类
     */
    @Operation(summary = "删除商品三级分类")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] catIds)
    {
        return toAjax(categoryService.deleteCategoryByCatIds(catIds));
    }
}
