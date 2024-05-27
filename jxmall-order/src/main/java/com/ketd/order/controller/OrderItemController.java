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

import com.ketd.order.domain.OrderItem;
import com.ketd.order.service.IOrderItemService;


/**
 * 订单项信息Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "订单项信息Controller")
@RestController
@RequestMapping("/order/OrderItem")
public class OrderItemController{

    @Autowired
    private IOrderItemService orderItemService;

    /**
     * 分页查询订单项信息列表
     */
    @Operation(summary ="分页查询订单项信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderItem> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        OrderItem orderItem= pageRequest.getData();
        Page<OrderItem> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>(orderItem);

        IPage<OrderItem> orderItemPage = orderItemService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderItemPage.getRecords(), orderItemPage.getTotal());



    }


    /**
     * 导出订单项信息列表
     */
    @Operation(summary = "导出订单项信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderItemIds)
    {
        List<OrderItem> list = orderItemService.listByIds(Arrays.asList(orderItemIds));
            orderItemService.export(list, response);

    }

    /**
     * 获取订单项信息详细信息
     */
    @Operation(summary = "获取订单项信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderItemService.selectOrderItemById(id));
    }

    /**
     * 新增订单项信息
     */
    @Operation(summary = "新增订单项信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody OrderItem orderItem)
    {
        return Result.ok(orderItemService.insertOrderItem(orderItem));
    }

    /**
     * 修改订单项信息
     */
    @Operation(summary = "修改订单项信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody OrderItem orderItem)
    {
        return Result.ok(orderItemService.updateOrderItem(orderItem));
    }

    /**
     * 删除订单项信息
     */
    @Operation(summary = "删除订单项信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderItemService.deleteOrderItemByIds(ids));
    }
}
