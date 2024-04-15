package com.ketd.product.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.Brand;
import com.ketd.product.service.IBrandService;


/**
 * 品牌Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "品牌Controller")
@RestController
@RequestMapping("/product/Brand")
public class BrandController{

    @Autowired
    private IBrandService brandService;

    /**
     * 分页查询品牌列表
     */
    @Operation(summary ="分页查询品牌列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Brand> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Brand brand= pageRequest.getData();
        Page<Brand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>(brand);

        IPage<Brand> brandPage = brandService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(brandPage.getRecords(), brandPage.getTotal());



    }


    /**
     * 导出品牌列表
     */
    @Operation(summary = "导出品牌列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] brandIds)
    {
        List<Brand> list = brandService.listByIds(Arrays.asList(brandIds));
            brandService.export(list, response);

    }

    /**
     * 获取品牌详细信息
     */
    @Operation(summary = "获取品牌详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("brandId") Long brandId)
    {
        return Result.ok(brandService.selectBrandByBrandId(brandId));
    }

    /**
     * 新增品牌
     */
    @Operation(summary = "新增品牌")
    @PostMapping("/save")
    public Result<?> add(@Valid @RequestBody Brand brand)
    {
        return Result.ok(brandService.insertBrand(brand));
    }

    /**
     * 修改品牌
     */
    @Operation(summary = "修改品牌")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Brand brand)
    {
        return Result.ok(brandService.updateBrand(brand));
    }

    /**
     * 删除品牌
     */
    @Operation(summary = "删除品牌")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] brandIds)
    {
        return Result.ok(brandService.deleteBrandByBrandIds(brandIds));
    }
}
