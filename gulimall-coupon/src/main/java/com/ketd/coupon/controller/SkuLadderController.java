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

import com.ketd.coupon.domain.SkuLadder;
import com.ketd.coupon.service.ISkuLadderService;


/**
 * 商品阶梯价格Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "商品阶梯价格Controller")
@RestController
@RequestMapping("/coupon/SkuLadder")
public class SkuLadderController{

    @Autowired
    private ISkuLadderService skuLadderService;

    /**
     * 分页查询商品阶梯价格列表
     */
    @Operation(summary ="分页查询商品阶梯价格列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SkuLadder> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SkuLadder skuLadder= pageRequest.getData();
        Page<SkuLadder> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SkuLadder> queryWrapper = new QueryWrapper<>(skuLadder);

        IPage<SkuLadder> skuLadderPage = skuLadderService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(skuLadderPage.getRecords(), skuLadderPage.getTotal());



    }


    /**
     * 导出商品阶梯价格列表
     */
    @Operation(summary = "导出商品阶梯价格列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] skuLadderIds)
    {
        List<SkuLadder> list = skuLadderService.listByIds(Arrays.asList(skuLadderIds));
            skuLadderService.export(list, response);

    }

    /**
     * 获取商品阶梯价格详细信息
     */
    @Operation(summary = "获取商品阶梯价格详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(skuLadderService.selectSkuLadderById(id));
    }

    /**
     * 新增商品阶梯价格
     */
    @Operation(summary = "新增商品阶梯价格")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SkuLadder skuLadder)
    {
        return Result.ok(skuLadderService.insertSkuLadder(skuLadder));
    }

    /**
     * 修改商品阶梯价格
     */
    @Operation(summary = "修改商品阶梯价格")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SkuLadder skuLadder)
    {
        return Result.ok(skuLadderService.updateSkuLadder(skuLadder));
    }

    /**
     * 删除商品阶梯价格
     */
    @Operation(summary = "删除商品阶梯价格")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(skuLadderService.deleteSkuLadderByIds(ids));
    }
}
