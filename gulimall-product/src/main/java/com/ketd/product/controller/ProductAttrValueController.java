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

import com.ketd.product.domain.ProductAttrValue;
import com.ketd.product.service.IProductAttrValueService;


/**
 * spu属性值Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "spu属性值Controller")
@RestController
@RequestMapping("/product/ProductAttrValue")
public class ProductAttrValueController{

    @Autowired
    private IProductAttrValueService productAttrValueService;

    /**
     * 分页查询spu属性值列表
     */
    @Operation(summary ="分页查询spu属性值列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<ProductAttrValue> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        ProductAttrValue productAttrValue= pageRequest.getData();
        Page<ProductAttrValue> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ProductAttrValue> queryWrapper = new QueryWrapper<>(productAttrValue);

        IPage<ProductAttrValue> productAttrValuePage = productAttrValueService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(productAttrValuePage.getRecords(), productAttrValuePage.getTotal());



    }


    /**
     * 导出spu属性值列表
     */
    @Operation(summary = "导出spu属性值列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] productAttrValueIds)
    {
        List<ProductAttrValue> list = productAttrValueService.listByIds(Arrays.asList(productAttrValueIds));
            productAttrValueService.export(list, response);

    }

    /**
     * 获取spu属性值详细信息
     */
    @Operation(summary = "获取spu属性值详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(productAttrValueService.selectProductAttrValueById(id));
    }

    /**
     * 新增spu属性值
     */
    @Operation(summary = "新增spu属性值")
    @PostMapping("/save")
    public Result<?> add(@RequestBody ProductAttrValue productAttrValue)
    {
        return Result.ok(productAttrValueService.insertProductAttrValue(productAttrValue));
    }

    /**
     * 修改spu属性值
     */
    @Operation(summary = "修改spu属性值")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody ProductAttrValue productAttrValue)
    {
        return Result.ok(productAttrValueService.updateProductAttrValue(productAttrValue));
    }

    /**
     * 删除spu属性值
     */
    @Operation(summary = "删除spu属性值")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(productAttrValueService.deleteProductAttrValueByIds(ids));
    }
}
