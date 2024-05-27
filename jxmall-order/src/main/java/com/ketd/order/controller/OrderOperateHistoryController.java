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

import com.ketd.order.domain.OrderOperateHistory;
import com.ketd.order.service.IOrderOperateHistoryService;


/**
 * 订单操作历史记录Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "订单操作历史记录Controller")
@RestController
@RequestMapping("/order/OrderOperateHistory")
public class OrderOperateHistoryController{

    @Autowired
    private IOrderOperateHistoryService orderOperateHistoryService;

    /**
     * 分页查询订单操作历史记录列表
     */
    @Operation(summary ="分页查询订单操作历史记录列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderOperateHistory> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        OrderOperateHistory orderOperateHistory= pageRequest.getData();
        Page<OrderOperateHistory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderOperateHistory> queryWrapper = new QueryWrapper<>(orderOperateHistory);

        IPage<OrderOperateHistory> orderOperateHistoryPage = orderOperateHistoryService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderOperateHistoryPage.getRecords(), orderOperateHistoryPage.getTotal());



    }


    /**
     * 导出订单操作历史记录列表
     */
    @Operation(summary = "导出订单操作历史记录列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderOperateHistoryIds)
    {
        List<OrderOperateHistory> list = orderOperateHistoryService.listByIds(Arrays.asList(orderOperateHistoryIds));
            orderOperateHistoryService.export(list, response);

    }

    /**
     * 获取订单操作历史记录详细信息
     */
    @Operation(summary = "获取订单操作历史记录详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderOperateHistoryService.selectOrderOperateHistoryById(id));
    }

    /**
     * 新增订单操作历史记录
     */
    @Operation(summary = "新增订单操作历史记录")
    @PostMapping("/save")
    public Result<?> add(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return Result.ok(orderOperateHistoryService.insertOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 修改订单操作历史记录
     */
    @Operation(summary = "修改订单操作历史记录")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return Result.ok(orderOperateHistoryService.updateOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 删除订单操作历史记录
     */
    @Operation(summary = "删除订单操作历史记录")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderOperateHistoryService.deleteOrderOperateHistoryByIds(ids));
    }
}
