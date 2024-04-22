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

import com.ketd.ware.domain.PurchaseDetail;
import com.ketd.ware.service.IPurchaseDetailService;


/**
 * 采购详情Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "采购详情Controller")
@RestController
@RequestMapping("/ware/PurchaseDetail")
public class PurchaseDetailController{

    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    /**
     * 分页查询采购详情列表
     */
    @Operation(summary ="分页查询采购详情列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<PurchaseDetail> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        PurchaseDetail purchaseDetail= pageRequest.getData();
        Page<PurchaseDetail> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PurchaseDetail> queryWrapper = new QueryWrapper<>(purchaseDetail);

        IPage<PurchaseDetail> purchaseDetailPage = purchaseDetailService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(purchaseDetailPage.getRecords(), purchaseDetailPage.getTotal());



    }


    /**
     * 导出采购详情列表
     */
    @Operation(summary = "导出采购详情列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] purchaseDetailIds)
    {
        List<PurchaseDetail> list = purchaseDetailService.listByIds(Arrays.asList(purchaseDetailIds));
            purchaseDetailService.export(list, response);

    }

    /**
     * 获取采购详情详细信息
     */
    @Operation(summary = "获取采购详情详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(purchaseDetailService.selectPurchaseDetailById(id));
    }

    /**
     * 新增采购详情
     */
    @Operation(summary = "新增采购详情")
    @PostMapping("/save")
    public Result<?> add(@RequestBody PurchaseDetail purchaseDetail)
    {
        return Result.ok(purchaseDetailService.insertPurchaseDetail(purchaseDetail));
    }

    /**
     * 修改采购详情
     */
    @Operation(summary = "修改采购详情")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody PurchaseDetail purchaseDetail)
    {
        return Result.ok(purchaseDetailService.updatePurchaseDetail(purchaseDetail));
    }

    /**
     * 删除采购详情
     */
    @Operation(summary = "删除采购详情")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(purchaseDetailService.deletePurchaseDetailByIds(ids));
    }
}
