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

import com.ketd.order.domain.Order;
import com.ketd.order.service.IOrderService;


/**
 * 订单Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "订单Controller")
@RestController
@RequestMapping("/order/Order")
public class OrderController{

    @Autowired
    private IOrderService orderService;

    /**
     * 分页查询订单列表
     */
    @Operation(summary ="分页查询订单列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Order> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Order order= pageRequest.getData();
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>(order);

        IPage<Order> orderPage = orderService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderPage.getRecords(), orderPage.getTotal());



    }


    /**
     * 导出订单列表
     */
    @Operation(summary = "导出订单列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderIds)
    {
        List<Order> list = orderService.listByIds(Arrays.asList(orderIds));
            orderService.export(list, response);

    }

    /**
     * 获取订单详细信息
     */
    @Operation(summary = "获取订单详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderService.selectOrderById(id));
    }

    /**
     * 新增订单
     */
    @Operation(summary = "新增订单")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Order order)
    {
        return Result.ok(orderService.insertOrder(order));
    }

    /**
     * 修改订单
     */
    @Operation(summary = "修改订单")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Order order)
    {
        return Result.ok(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @Operation(summary = "删除订单")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderService.deleteOrderByIds(ids));
    }
}
