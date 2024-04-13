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

import com.ketd.common.BaseController;
import com.ketd.common.AjaxResult;
import com.ketd.common.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.AttrGroup;
import com.ketd.product.service.IAttrGroupService;


/**
 * 属性分组Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "属性分组Controller")
@RestController
@RequestMapping("/product/AttrGroup")
public class AttrGroupController extends BaseController{

    @Autowired
    private IAttrGroupService attrGroupService;

    /**
     * 分页查询属性分组列表
     */
    @Operation(summary ="分页查询属性分组列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrGroup> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        AttrGroup attrGroup= pageRequest.getData();
        Page<AttrGroup> page = new Page<>(pageNum, pageSize);
        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>(attrGroup);

        IPage<AttrGroup> attrGroupPage = attrGroupService.page(page, queryWrapper);
        return getDataTable(attrGroupPage.getRecords(), attrGroupPage.getTotal());



    }


    /**
     * 导出属性分组列表
     */
    @Operation(summary = "导出属性分组列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] attrGroupIds)
    {
        List<AttrGroup> list = attrGroupService.listByIds(Arrays.asList(attrGroupIds));
            attrGroupService.export(list, response);

    }

    /**
     * 获取属性分组详细信息
     */
    @Operation(summary = "获取属性分组详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("attrGroupId") Long attrGroupId)
    {
        return success(attrGroupService.selectAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @Operation(summary = "新增属性分组")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.insertAttrGroup(attrGroup));
    }

    /**
     * 修改属性分组
     */
    @Operation(summary = "修改属性分组")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.updateAttrGroup(attrGroup));
    }

    /**
     * 删除属性分组
     */
    @Operation(summary = "删除属性分组")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] attrGroupIds)
    {
        return toAjax(attrGroupService.deleteAttrGroupByAttrGroupIds(attrGroupIds));
    }
}
