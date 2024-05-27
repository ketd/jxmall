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

import com.ketd.order.domain.OrderReturnApply;
import com.ketd.order.service.IOrderReturnApplyService;


/**
 * 订单退货申请Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "订单退货申请Controller")
@RestController
@RequestMapping("/order/OrderReturnApply")
public class OrderReturnApplyController{

    @Autowired
    private IOrderReturnApplyService orderReturnApplyService;

    /**
     * 分页查询订单退货申请列表
     */
    @Operation(summary ="分页查询订单退货申请列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderReturnApply> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        OrderReturnApply orderReturnApply= pageRequest.getData();
        Page<OrderReturnApply> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderReturnApply> queryWrapper = new QueryWrapper<>(orderReturnApply);

        IPage<OrderReturnApply> orderReturnApplyPage = orderReturnApplyService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderReturnApplyPage.getRecords(), orderReturnApplyPage.getTotal());



    }


    /**
     * 导出订单退货申请列表
     */
    @Operation(summary = "导出订单退货申请列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderReturnApplyIds)
    {
        List<OrderReturnApply> list = orderReturnApplyService.listByIds(Arrays.asList(orderReturnApplyIds));
            orderReturnApplyService.export(list, response);

    }

    /**
     * 获取订单退货申请详细信息
     */
    @Operation(summary = "获取订单退货申请详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderReturnApplyService.selectOrderReturnApplyById(id));
    }

    /**
     * 新增订单退货申请
     */
    @Operation(summary = "新增订单退货申请")
    @PostMapping("/save")
    public Result<?> add(@RequestBody OrderReturnApply orderReturnApply)
    {
        return Result.ok(orderReturnApplyService.insertOrderReturnApply(orderReturnApply));
    }

    /**
     * 修改订单退货申请
     */
    @Operation(summary = "修改订单退货申请")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody OrderReturnApply orderReturnApply)
    {
        return Result.ok(orderReturnApplyService.updateOrderReturnApply(orderReturnApply));
    }

    /**
     * 删除订单退货申请
     */
    @Operation(summary = "删除订单退货申请")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderReturnApplyService.deleteOrderReturnApplyByIds(ids));
    }
}
