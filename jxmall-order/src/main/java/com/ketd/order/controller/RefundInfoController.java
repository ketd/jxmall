package com.ketd.order.controller;




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

import com.ketd.order.domain.RefundInfo;
import com.ketd.order.service.IRefundInfoService;


/**
 * 退款信息Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "退款信息Controller")
@RestController
@RequestMapping("/order/RefundInfo")
public class RefundInfoController{

    @Autowired
    private IRefundInfoService refundInfoService;

    /**
     * 分页查询退款信息列表
     */
    @Operation(summary ="分页查询退款信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<RefundInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        RefundInfo refundInfo= pageRequest.getData();
        Page<RefundInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<RefundInfo> queryWrapper = new QueryWrapper<>(refundInfo);

        IPage<RefundInfo> refundInfoPage = refundInfoService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(refundInfoPage.getRecords(), refundInfoPage.getTotal());



    }


    /**
     * 导出退款信息列表
     */
    @Operation(summary = "导出退款信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] refundInfoIds)
    {
        List<RefundInfo> list = refundInfoService.listByIds(Arrays.asList(refundInfoIds));
            refundInfoService.export(list, response);

    }

    /**
     * 获取退款信息详细信息
     */
    @Operation(summary = "获取退款信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(refundInfoService.selectRefundInfoById(id));
    }

    /**
     * 新增退款信息
     */
    @Operation(summary = "新增退款信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody RefundInfo refundInfo)
    {
        return Result.ok(refundInfoService.insertRefundInfo(refundInfo));
    }

    /**
     * 修改退款信息
     */
    @Operation(summary = "修改退款信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody RefundInfo refundInfo)
    {
        return Result.ok(refundInfoService.updateRefundInfo(refundInfo));
    }

    /**
     * 删除退款信息
     */
    @Operation(summary = "删除退款信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(refundInfoService.deleteRefundInfoByIds(ids));
    }
}
