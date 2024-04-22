package com.ketd.coupon.controller;




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

import com.ketd.coupon.domain.SkuFullReduction;
import com.ketd.coupon.service.ISkuFullReductionService;


/**
 * 商品满减信息Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "商品满减信息Controller")
@RestController
@RequestMapping("/coupon/SkuFullReduction")
public class SkuFullReductionController{

    @Autowired
    private ISkuFullReductionService skuFullReductionService;

    /**
     * 分页查询商品满减信息列表
     */
    @Operation(summary ="分页查询商品满减信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuFullReduction> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SkuFullReduction skuFullReduction= pageRequest.getData();
        Page<SkuFullReduction> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkuFullReduction> queryWrapper = new QueryWrapper<>(skuFullReduction);

        IPage<SkuFullReduction> skuFullReductionPage = skuFullReductionService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(skuFullReductionPage.getRecords(), skuFullReductionPage.getTotal());



    }


    /**
     * 导出商品满减信息列表
     */
    @Operation(summary = "导出商品满减信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] skuFullReductionIds)
    {
        List<SkuFullReduction> list = skuFullReductionService.listByIds(Arrays.asList(skuFullReductionIds));
            skuFullReductionService.export(list, response);

    }

    /**
     * 获取商品满减信息详细信息
     */
    @Operation(summary = "获取商品满减信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(skuFullReductionService.selectSkuFullReductionById(id));
    }

    /**
     * 新增商品满减信息
     */
    @Operation(summary = "新增商品满减信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SkuFullReduction skuFullReduction)
    {
        return Result.ok(skuFullReductionService.insertSkuFullReduction(skuFullReduction));
    }

    /**
     * 修改商品满减信息
     */
    @Operation(summary = "修改商品满减信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SkuFullReduction skuFullReduction)
    {
        return Result.ok(skuFullReductionService.updateSkuFullReduction(skuFullReduction));
    }

    /**
     * 删除商品满减信息
     */
    @Operation(summary = "删除商品满减信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(skuFullReductionService.deleteSkuFullReductionByIds(ids));
    }
}
