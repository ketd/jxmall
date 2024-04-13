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

import com.ketd.product.domain.AttrAttrgroupRelation;
import com.ketd.product.service.IAttrAttrgroupRelationService;


/**
 * 属性&属性分组关联Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "属性&属性分组关联Controller")
@RestController
@RequestMapping("/product/AttrAttrgroupRelation")
public class AttrAttrgroupRelationController extends BaseController{

    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 分页查询属性&属性分组关联列表
     */
    @Operation(summary ="分页查询属性&属性分组关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<AttrAttrgroupRelation> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        AttrAttrgroupRelation attrAttrgroupRelation= pageRequest.getData();
        Page<AttrAttrgroupRelation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<AttrAttrgroupRelation> queryWrapper = new QueryWrapper<>(attrAttrgroupRelation);

        IPage<AttrAttrgroupRelation> attrAttrgroupRelationPage = attrAttrgroupRelationService.page(page, queryWrapper);
        return getDataTable(attrAttrgroupRelationPage.getRecords(), attrAttrgroupRelationPage.getTotal());



    }


    /**
     * 导出属性&属性分组关联列表
     */
    @Operation(summary = "导出属性&属性分组关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] attrAttrgroupRelationIds)
    {
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.listByIds(Arrays.asList(attrAttrgroupRelationIds));
            attrAttrgroupRelationService.export(list, response);

    }

    /**
     * 获取属性&属性分组关联详细信息
     */
    @Operation(summary = "获取属性&属性分组关联详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("id") Long id)
    {
        return success(attrAttrgroupRelationService.selectAttrAttrgroupRelationById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @Operation(summary = "新增属性&属性分组关联")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return toAjax(attrAttrgroupRelationService.insertAttrAttrgroupRelation(attrAttrgroupRelation));
    }

    /**
     * 修改属性&属性分组关联
     */
    @Operation(summary = "修改属性&属性分组关联")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return toAjax(attrAttrgroupRelationService.updateAttrAttrgroupRelation(attrAttrgroupRelation));
    }

    /**
     * 删除属性&属性分组关联
     */
    @Operation(summary = "删除属性&属性分组关联")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(attrAttrgroupRelationService.deleteAttrAttrgroupRelationByIds(ids));
    }
}
