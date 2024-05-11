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

import com.ketd.product.domain.SpuComment;
import com.ketd.product.service.ISpuCommentService;


/**
 * 商品评价Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "商品评价Controller")
@RestController
@RequestMapping("/product/SpuComment")
public class SpuCommentController{

    @Autowired
    private ISpuCommentService spuCommentService;

    /**
     * 分页查询商品评价列表
     */
    @Operation(summary ="分页查询商品评价列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SpuComment> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SpuComment spuComment= pageRequest.getData();
        Page<SpuComment> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SpuComment> queryWrapper = new QueryWrapper<>(spuComment);

        IPage<SpuComment> spuCommentPage = spuCommentService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(spuCommentPage.getRecords(), spuCommentPage.getTotal());



    }


    /**
     * 导出商品评价列表
     */
    @Operation(summary = "导出商品评价列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] spuCommentIds)
    {
        List<SpuComment> list = spuCommentService.listByIds(Arrays.asList(spuCommentIds));
            spuCommentService.export(list, response);

    }

    /**
     * 获取商品评价详细信息
     */
    @Operation(summary = "获取商品评价详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(spuCommentService.selectSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @Operation(summary = "新增商品评价")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SpuComment spuComment)
    {
        return Result.ok(spuCommentService.insertSpuComment(spuComment));
    }

    /**
     * 修改商品评价
     */
    @Operation(summary = "修改商品评价")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SpuComment spuComment)
    {
        return Result.ok(spuCommentService.updateSpuComment(spuComment));
    }

    /**
     * 删除商品评价
     */
    @Operation(summary = "删除商品评价")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(spuCommentService.deleteSpuCommentByIds(ids));
    }
}
