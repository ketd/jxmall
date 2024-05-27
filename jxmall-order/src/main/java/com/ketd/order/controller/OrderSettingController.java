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

import com.ketd.order.domain.OrderSetting;
import com.ketd.order.service.IOrderSettingService;


/**
 * 订单配置信息Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "订单配置信息Controller")
@RestController
@RequestMapping("/order/OrderSetting")
public class OrderSettingController{

    @Autowired
    private IOrderSettingService orderSettingService;

    /**
     * 分页查询订单配置信息列表
     */
    @Operation(summary ="分页查询订单配置信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderSetting> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        OrderSetting orderSetting= pageRequest.getData();
        Page<OrderSetting> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderSetting> queryWrapper = new QueryWrapper<>(orderSetting);

        IPage<OrderSetting> orderSettingPage = orderSettingService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderSettingPage.getRecords(), orderSettingPage.getTotal());



    }


    /**
     * 导出订单配置信息列表
     */
    @Operation(summary = "导出订单配置信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderSettingIds)
    {
        List<OrderSetting> list = orderSettingService.listByIds(Arrays.asList(orderSettingIds));
            orderSettingService.export(list, response);

    }

    /**
     * 获取订单配置信息详细信息
     */
    @Operation(summary = "获取订单配置信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderSettingService.selectOrderSettingById(id));
    }

    /**
     * 新增订单配置信息
     */
    @Operation(summary = "新增订单配置信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody OrderSetting orderSetting)
    {
        return Result.ok(orderSettingService.insertOrderSetting(orderSetting));
    }

    /**
     * 修改订单配置信息
     */
    @Operation(summary = "修改订单配置信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody OrderSetting orderSetting)
    {
        return Result.ok(orderSettingService.updateOrderSetting(orderSetting));
    }

    /**
     * 删除订单配置信息
     */
    @Operation(summary = "删除订单配置信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderSettingService.deleteOrderSettingByIds(ids));
    }
}
