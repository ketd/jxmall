package com.ketd.ware.controller;




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

import com.ketd.ware.domain.WareSku;
import com.ketd.ware.service.IWareSkuService;


/**
 * 商品库存Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "商品库存Controller")
@RestController
@RequestMapping("/ware/WareSku")
public class WareSkuController{

    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 分页查询商品库存列表
     */
    @Operation(summary ="分页查询商品库存列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<WareSku> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        WareSku wareSku= pageRequest.getData();
        Page<WareSku> page = new Page<>(pageNum, pageSize);
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>(wareSku);

        IPage<WareSku> wareSkuPage = wareSkuService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(wareSkuPage.getRecords(), wareSkuPage.getTotal());



    }


    /**
     * 导出商品库存列表
     */
    @Operation(summary = "导出商品库存列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] wareSkuIds)
    {
        List<WareSku> list = wareSkuService.listByIds(Arrays.asList(wareSkuIds));
            wareSkuService.export(list, response);

    }

    /**
     * 获取商品库存详细信息
     */
    @Operation(summary = "获取商品库存详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(wareSkuService.selectWareSkuById(id));
    }

    /**
     * 新增商品库存
     */
    @Operation(summary = "新增商品库存")
    @PostMapping("/save")
    public Result<?> add(@RequestBody WareSku wareSku)
    {
        return Result.ok(wareSkuService.insertWareSku(wareSku));
    }

    /**
     * 修改商品库存
     */
    @Operation(summary = "修改商品库存")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody WareSku wareSku)
    {
        return Result.ok(wareSkuService.updateWareSku(wareSku));
    }

    /**
     * 删除商品库存
     */
    @Operation(summary = "删除商品库存")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(wareSkuService.deleteWareSkuByIds(ids));
    }

    //查询是否有库存
    @Operation(summary = "查询是否有库存")
    @PostMapping("/hasStock")
    public Result<?> hasStock(@RequestBody List<Long> skuIds)
    {
        return wareSkuService.hasStock(skuIds);
    }

    //查询是否有库存
    @Operation(summary = "查询库存是否足够")
    @GetMapping("/hasStockByCount")
    public Result<?> hasStockByCount(@RequestParam(value = "skuId") Long skuId,@RequestParam(value = "count") Integer count)
    {
        return wareSkuService.hasStockByCount(skuId,count);
    }
}
