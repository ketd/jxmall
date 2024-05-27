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

import com.ketd.order.domain.OrderReturnReason;
import com.ketd.order.service.IOrderReturnReasonService;


/**
 * 退货原因Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "退货原因Controller")
@RestController
@RequestMapping("/order/OrderReturnReason")
public class OrderReturnReasonController{

    @Autowired
    private IOrderReturnReasonService orderReturnReasonService;

    /**
     * 分页查询退货原因列表
     */
    @Operation(summary ="分页查询退货原因列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<OrderReturnReason> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        OrderReturnReason orderReturnReason= pageRequest.getData();
        Page<OrderReturnReason> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderReturnReason> queryWrapper = new QueryWrapper<>(orderReturnReason);

        IPage<OrderReturnReason> orderReturnReasonPage = orderReturnReasonService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(orderReturnReasonPage.getRecords(), orderReturnReasonPage.getTotal());



    }


    /**
     * 导出退货原因列表
     */
    @Operation(summary = "导出退货原因列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] orderReturnReasonIds)
    {
        List<OrderReturnReason> list = orderReturnReasonService.listByIds(Arrays.asList(orderReturnReasonIds));
            orderReturnReasonService.export(list, response);

    }

    /**
     * 获取退货原因详细信息
     */
    @Operation(summary = "获取退货原因详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(orderReturnReasonService.selectOrderReturnReasonById(id));
    }

    /**
     * 新增退货原因
     */
    @Operation(summary = "新增退货原因")
    @PostMapping("/save")
    public Result<?> add(@RequestBody OrderReturnReason orderReturnReason)
    {
        return Result.ok(orderReturnReasonService.insertOrderReturnReason(orderReturnReason));
    }

    /**
     * 修改退货原因
     */
    @Operation(summary = "修改退货原因")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody OrderReturnReason orderReturnReason)
    {
        return Result.ok(orderReturnReasonService.updateOrderReturnReason(orderReturnReason));
    }

    /**
     * 删除退货原因
     */
    @Operation(summary = "删除退货原因")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(orderReturnReasonService.deleteOrderReturnReasonByIds(ids));
    }
}
