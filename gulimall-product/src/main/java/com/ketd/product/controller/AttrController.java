package com.ketd.product.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ketd.product.domain.AttrGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.Attr;
import com.ketd.product.service.IAttrService;


/**
 * 商品属性Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@Tag(name = "商品属性Controller")
@RestController
@RequestMapping("/product/Attr")
public class AttrController{

    @Autowired
    private IAttrService attrService;

    /**
     * 分页查询商品属性列表
     */
    @Operation(summary ="分页查询商品属性列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Attr> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Attr attr= pageRequest.getData();
        Page<Attr> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attr);

        IPage<Attr> attrPage = attrService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(attrPage.getRecords(), attrPage.getTotal());



    }


    /**
     * 导出商品属性列表
     */
    @Operation(summary = "导出商品属性列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] attrIds)
    {
        List<Attr> list = attrService.listByIds(Arrays.asList(attrIds));
            attrService.export(list, response);

    }

    /**
     * 获取商品属性详细信息
     */
    @Operation(summary = "获取商品属性详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("attrId") Long attrId) {

        return Result.ok(attrService.selectAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @Operation(summary = "新增商品属性")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Attr attr)
    {
        return Result.ok(attrService.insertAttr(attr));
    }

    /**
     * 修改商品属性
     */
    @Operation(summary = "修改商品属性")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Attr attr)
    {
        return Result.ok(attrService.updateAttr(attr));
    }

    /**
     * 删除商品属性
     */
    @Operation(summary = "删除商品属性")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] attrIds)
    {
        return Result.ok(attrService.deleteAttrByAttrIds(attrIds));
    }
}
