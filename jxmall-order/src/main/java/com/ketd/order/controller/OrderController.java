package com.ketd.order.controller;




import java.util.Arrays;
import java.util.List;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ketd.order.vo.SkuCountVo;
import com.ketd.order.vo.SubmitOrderVo;
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

    @Operation(summary = "生成订单")
    @PostMapping("toTrade")
        public Result<?> toTrade(@RequestBody List<SkuCountVo> skuCountVos){
        return orderService.toTrade(skuCountVos);
    }

    @Operation(summary = "提交订单")
    @PostMapping("submit")
    public Result<?> submitOrder(@RequestBody SubmitOrderVo submitOrderVo){
        return orderService.submitOrder(submitOrderVo);
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

    @Operation(summary = "获取订单详细信息通过订单号")
    @GetMapping(value = "/infoByOrderSn")
    public Result<?> getInfoByOrderSn(@RequestParam("orderSn") String orderSn)
    {
        return orderService.getInfoByOrderSn(orderSn);
    }

    @Operation(summary = "获取订单详细信息通过订单号")
    @PutMapping(value = "/updateStatusByOrderSn")
    public Result<?> updateStatusByOrderSn(@RequestParam("status") Integer status,@RequestParam("orderSn") String orderSn)
    {
        return orderService.updateStatusByOrderSn(status,orderSn);
    }

    @Operation(summary = "获取用户订单详细信息")
    @GetMapping(value = "/info/member")
    public Result<?> getMemberOrderInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderService.getMemberOrderInfo(id));
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

    @Operation(summary = "用户删除订单")
    @DeleteMapping("/member/delete")
    public Result<?> remove(@RequestParam(value = "id") Long id)
    {
        return orderService.deleteOrderById(id);
    }

    @GetMapping(value = "/getMemberOrders")
    public Result<?> getMemberOrders(@RequestParam(value = "status", required = false) Integer status) {
        return orderService.getMemberOrders(status);
    }

}
