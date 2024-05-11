package com.ketd.product.controller;






import com.ketd.product.vo.AttrVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.Attr;
import com.ketd.product.service.IAttrService;

import java.util.Arrays;
import java.util.List;


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
    @PostMapping("/base/list/page")
    public Result<?> attrBaseList(@RequestBody PageRequest<Attr> pageRequest)
    {
        return attrService.pageAttrList(pageRequest);
    }

    /**
     * 分页查询商品属性列表
     */
    @Operation(summary ="查询商品销售属性属性列表")
    @PostMapping("/sale/list/page")
    public Result<?> attrSaleList(@RequestParam(value = "catalogId") Long catalogId)
    {
        return Result.ok(attrService.pageAttrSaleList(catalogId));
    }

    @Operation(summary ="分页查询为连接的商品属性列表")
    @PostMapping("/base/list/noBindAttrList/page")
    public Result<?> noLinkAttrList(@RequestBody PageRequest<Attr> pageRequest)
    {
        return attrService.noLinkAttrList(pageRequest);
    }

    @Operation(summary ="获取info")
    @PostMapping("/base/list/getInfo")
    public Result<?> getInfoByCatelogId(@RequestParam(value = "attrId")  Long catelogId)
    {
        return attrService.getInfoByCatelogId(catelogId);
    }

    @Operation(summary ="分页查询分组关联的规格参数")
    @PostMapping("/base/list/findAllAttrByAttrGroupId/page")
    public Result<?>  findAttrGroupRelation(@RequestBody PageRequest<Long> pageRequest )
    {
        List<Attr> attrList = attrService.findAllAttrByAttrGroupId(pageRequest);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(attrList.size());
        tableDataInfo.setRows(attrList);
        return Result.ok(tableDataInfo);
    }

    @Operation(summary ="分页查询分组关联的规格参数")
    @GetMapping("/base/list/findAttrByCatelogId")
    public Result<?>  findAttrByCatelogId(@RequestParam(value = "catelogId") Long catelogId)
    {
        return attrService.findAttrByCatelogId(catelogId);

    }

    @Operation(summary ="连接商品属性")
    @PostMapping("/link")
    public Result<?> linkAttr(@RequestParam(value = "attrId") Long attrGroupId,@RequestBody  Long[] attrId )
    {
        return attrService.linkAttr(attrGroupId, attrId);
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

        return attrService.selectAttrByAttrId(attrId);
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
    public Result<?> edit(@RequestBody AttrVo attrVo)
    {
        return Result.ok(attrService.updateAttr(attrVo));
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
