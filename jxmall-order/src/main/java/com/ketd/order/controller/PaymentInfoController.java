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

import com.ketd.order.domain.PaymentInfo;
import com.ketd.order.service.IPaymentInfoService;


/**
 * 支付信息Controller
 *
 * @author ketd
 * @date 2024-05-27
 */
@Tag(name = "支付信息Controller")
@RestController
@RequestMapping("/order/PaymentInfo")
public class PaymentInfoController{

    @Autowired
    private IPaymentInfoService paymentInfoService;

    /**
     * 分页查询支付信息列表
     */
    @Operation(summary ="分页查询支付信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<PaymentInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        PaymentInfo paymentInfo= pageRequest.getData();
        Page<PaymentInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>(paymentInfo);

        IPage<PaymentInfo> paymentInfoPage = paymentInfoService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(paymentInfoPage.getRecords(), paymentInfoPage.getTotal());



    }


    /**
     * 导出支付信息列表
     */
    @Operation(summary = "导出支付信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] paymentInfoIds)
    {
        List<PaymentInfo> list = paymentInfoService.listByIds(Arrays.asList(paymentInfoIds));
            paymentInfoService.export(list, response);

    }

    /**
     * 获取支付信息详细信息
     */
    @Operation(summary = "获取支付信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(paymentInfoService.selectPaymentInfoById(id));
    }

    @Operation(summary = "获取支付信息详细信息")
    @GetMapping(value = "/getInfoBySn")
    public Result<?> getInfoBySn(@RequestParam("orderSn") String orderSn)
    {
        return paymentInfoService.getInfoBySn(orderSn);
    }


    /**
     * 新增支付信息
     */
    @Operation(summary = "新增支付信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody PaymentInfo paymentInfo)
    {
        return Result.ok(paymentInfoService.insertPaymentInfo(paymentInfo));
    }

    /**
     * 修改支付信息
     */
    @Operation(summary = "修改支付信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody PaymentInfo paymentInfo)
    {
        return Result.ok(paymentInfoService.updatePaymentInfo(paymentInfo));
    }

    /**
     * 删除支付信息
     */
    @Operation(summary = "删除支付信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(paymentInfoService.deletePaymentInfoByIds(ids));
    }
}
