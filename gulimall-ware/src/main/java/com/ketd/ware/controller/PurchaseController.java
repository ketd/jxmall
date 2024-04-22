package com.ketd.ware.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ketd.ware.vo.MergeVo;
import com.ketd.ware.vo.PurchaseDoneVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.ware.domain.Purchase;
import com.ketd.ware.service.IPurchaseService;


/**
 * 采购信息Controller
 *
 * @author ketd
 * @date 2024-04-21
 */
@Tag(name = "采购单Controller")
@RestController
@RequestMapping("/ware/Purchase")
public class PurchaseController{

    @Autowired
    private IPurchaseService purchaseService;

    /**
     * 分页查询采购信息列表
     */
    @Operation(summary ="分页查询采购信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Purchase> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Purchase purchase= pageRequest.getData();
        Page<Purchase> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>(purchase);

        IPage<Purchase> purchasePage = purchaseService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(purchasePage.getRecords(), purchasePage.getTotal());



    }

    @Operation(summary ="合并采购项")
    @PostMapping("/merge")
    public Result<?> merge(@RequestBody MergeVo  mergeVo){

        return purchaseService.mergePurchaseDetail(mergeVo);
    }

    @Operation(summary ="领取采购单")
    @PostMapping("/receive")
    public Result<?>  receive(@RequestBody Long[] ids){

        return purchaseService.receive(ids);
    }

    @Operation(summary ="完成采购单")
    @PostMapping("/done")
    public Result<?>  done(@RequestBody PurchaseDoneVo doneVo){

        return purchaseService.done(doneVo);
    }

    /**
     * 导出采购信息列表
     */
    @Operation(summary = "导出采购信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] purchaseIds)
    {
        List<Purchase> list = purchaseService.listByIds(Arrays.asList(purchaseIds));
            purchaseService.export(list, response);

    }

    /**
     * 获取采购信息详细信息
     */
    @Operation(summary = "获取采购信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(purchaseService.selectPurchaseById(id));
    }

    /**
     * 新增采购信息
     */
    @Operation(summary = "新增采购信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Purchase purchase)
    {
        return Result.ok(purchaseService.insertPurchase(purchase));
    }

    /**
     * 修改采购信息
     */
    @Operation(summary = "修改采购信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Purchase purchase)
    {
        return Result.ok(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除采购信息
     */
    @Operation(summary = "删除采购信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(purchaseService.deletePurchaseByIds(ids));
    }
}
