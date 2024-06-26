package com.ketd.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.result.Result;
import com.ketd.product.domain.AttrGroup;
import com.ketd.product.service.IAttrGroupService;
import com.ketd.product.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 属性分组Controller
 *
 * @author ketd
 * @date 2024-04-13
 */
@NoArgsConstructor
@Data
@Tag(name = "属性分组Controller")
@RestController
@RequestMapping("/product/AttrGroup")
public class AttrGroupController {

    @Autowired
    private IAttrGroupService attrGroupService;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 分页查询属性分组列表
     */
    @Operation(summary = "分页查询属性分组列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrGroup> pageRequest) {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        AttrGroup attrGroup = pageRequest.getData();
        Page<AttrGroup> page = new Page<>(pageNum, pageSize);
        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>(attrGroup);

        IPage<AttrGroup> attrGroupPage = attrGroupService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(attrGroupPage.getRecords(), attrGroupPage.getTotal());


    }


    /**
     * 导出属性分组列表
     */
    @Operation(summary = "导出属性分组列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] attrGroupIds) {
        List<AttrGroup> list = attrGroupService.listByIds(Arrays.asList(attrGroupIds));
        attrGroupService.export(list, response);

    }


    @Operation(summary = "获取属性分组详细信息")
    @GetMapping("/list/withoutAttrs")
    public Result<?> getAttrGroupWithoutAttrs(@RequestParam("cateLogId") Long cateLogId) {


        return Result.ok(attrGroupService.getAttrGroupWithoutAttrs(cateLogId));

    }

    /**
     * 获取属性分组详细信息
     */
    @Operation(summary = "获取属性分组详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("attrGroupId") Long attrGroupId) {
        AttrGroup attrGroup = attrGroupService.selectAttrGroupByAttrGroupId(attrGroupId);
        Long[] path = categoryService.findCategoryPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(path);
        return Result.ok(attrGroup);
    }

    /**
     * 新增属性分组
     */
    @Operation(summary = "新增属性分组")
    @PostMapping("/save")
    public Result<?> add(@RequestBody AttrGroup attrGroup) {
        return Result.ok(attrGroupService.insertAttrGroup(attrGroup));
    }

    /**
     * 修改属性分组
     */
    @Operation(summary = "修改属性分组")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody AttrGroup attrGroup) {
        return Result.ok(attrGroupService.updateAttrGroup(attrGroup));
    }

    /**
     * 删除属性分组
     */
    @Operation(summary = "删除属性分组")
    @DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] attrGroupIds) {
        return Result.ok(attrGroupService.deleteAttrGroupByAttrGroupIds(attrGroupIds));
    }

}
