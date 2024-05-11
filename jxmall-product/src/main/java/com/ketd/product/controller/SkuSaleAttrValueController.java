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

import com.ketd.product.domain.SkuSaleAttrValue;
import com.ketd.product.service.ISkuSaleAttrValueService;


/**
 * sku销售属性&值Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "sku销售属性&值Controller")
@RestController
@RequestMapping("/product/SkuSaleAttrValue")
public class SkuSaleAttrValueController{

    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 分页查询sku销售属性&值列表
     */
    @Operation(summary ="分页查询sku销售属性&值列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuSaleAttrValue> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SkuSaleAttrValue skuSaleAttrValue= pageRequest.getData();
        Page<SkuSaleAttrValue> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkuSaleAttrValue> queryWrapper = new QueryWrapper<>(skuSaleAttrValue);

        IPage<SkuSaleAttrValue> skuSaleAttrValuePage = skuSaleAttrValueService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(skuSaleAttrValuePage.getRecords(), skuSaleAttrValuePage.getTotal());



    }


    /**
     * 导出sku销售属性&值列表
     */
    @Operation(summary = "导出sku销售属性&值列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] skuSaleAttrValueIds)
    {
        List<SkuSaleAttrValue> list = skuSaleAttrValueService.listByIds(Arrays.asList(skuSaleAttrValueIds));
            skuSaleAttrValueService.export(list, response);

    }

    /**
     * 获取sku销售属性&值详细信息
     */
    @Operation(summary = "获取sku销售属性&值详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(skuSaleAttrValueService.selectSkuSaleAttrValueById(id));
    }

    /**
     * 新增sku销售属性&值
     */
    @Operation(summary = "新增sku销售属性&值")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SkuSaleAttrValue skuSaleAttrValue)
    {
        return Result.ok(skuSaleAttrValueService.insertSkuSaleAttrValue(skuSaleAttrValue));
    }

    /**
     * 修改sku销售属性&值
     */
    @Operation(summary = "修改sku销售属性&值")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SkuSaleAttrValue skuSaleAttrValue)
    {
        return Result.ok(skuSaleAttrValueService.updateSkuSaleAttrValue(skuSaleAttrValue));
    }

    /**
     * 删除sku销售属性&值
     */
    @Operation(summary = "删除sku销售属性&值")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(skuSaleAttrValueService.deleteSkuSaleAttrValueByIds(ids));
    }
}
