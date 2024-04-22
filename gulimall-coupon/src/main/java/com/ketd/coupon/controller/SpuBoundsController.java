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

import com.ketd.coupon.domain.SpuBounds;
import com.ketd.coupon.service.ISpuBoundsService;


/**
 * 商品spu积分设置Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "商品spu积分设置Controller")
@RestController
@RequestMapping("/coupon/SpuBounds")
public class SpuBoundsController{

    @Autowired
    private ISpuBoundsService spuBoundsService;

    /**
     * 分页查询商品spu积分设置列表
     */
    @Operation(summary ="分页查询商品spu积分设置列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuBounds> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SpuBounds spuBounds= pageRequest.getData();
        Page<SpuBounds> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuBounds> queryWrapper = new QueryWrapper<>(spuBounds);

        IPage<SpuBounds> spuBoundsPage = spuBoundsService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(spuBoundsPage.getRecords(), spuBoundsPage.getTotal());



    }


    /**
     * 导出商品spu积分设置列表
     */
    @Operation(summary = "导出商品spu积分设置列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] spuBoundsIds)
    {
        List<SpuBounds> list = spuBoundsService.listByIds(Arrays.asList(spuBoundsIds));
            spuBoundsService.export(list, response);

    }

    /**
     * 获取商品spu积分设置详细信息
     */
    @Operation(summary = "获取商品spu积分设置详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(spuBoundsService.selectSpuBoundsById(id));
    }

    /**
     * 新增商品spu积分设置
     */
    @Operation(summary = "新增商品spu积分设置")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SpuBounds spuBounds)
    {
        return Result.ok(spuBoundsService.insertSpuBounds(spuBounds));
    }

    /**
     * 修改商品spu积分设置
     */
    @Operation(summary = "修改商品spu积分设置")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SpuBounds spuBounds)
    {
        return Result.ok(spuBoundsService.updateSpuBounds(spuBounds));
    }

    /**
     * 删除商品spu积分设置
     */
    @Operation(summary = "删除商品spu积分设置")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(spuBoundsService.deleteSpuBoundsByIds(ids));
    }
}
