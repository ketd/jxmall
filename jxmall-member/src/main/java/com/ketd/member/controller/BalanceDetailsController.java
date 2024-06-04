package com.ketd.member.controller;




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

import com.ketd.member.domain.BalanceDetails;
import com.ketd.member.service.IBalanceDetailsService;


/**
 * 用户余额明细Controller
 *
 * @author ketd
 * @date 2024-06-04
 */
@Tag(name = "用户余额明细Controller")
@RestController
@RequestMapping("/member/BalanceDetails")
public class BalanceDetailsController{

    @Autowired
    private IBalanceDetailsService balanceDetailsService;

    /**
     * 分页查询用户余额明细列表
     */
    @Operation(summary ="分页查询用户余额明细列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<BalanceDetails> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        BalanceDetails balanceDetails= pageRequest.getData();
        Page<BalanceDetails> page = new Page<>(pageNum, pageSize);
        QueryWrapper<BalanceDetails> queryWrapper = new QueryWrapper<>(balanceDetails);

        IPage<BalanceDetails> balanceDetailsPage = balanceDetailsService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(balanceDetailsPage.getRecords(), balanceDetailsPage.getTotal());



    }


    /**
     * 导出用户余额明细列表
     */
    @Operation(summary = "导出用户余额明细列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] balanceDetailsIds)
    {
        List<BalanceDetails> list = balanceDetailsService.listByIds(Arrays.asList(balanceDetailsIds));
        balanceDetailsService.export(list, response);

    }

    /**
     * 获取用户余额明细详细信息
     */
    @Operation(summary = "获取用户余额明细详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(balanceDetailsService.selectBalanceDetailsById(id));
    }

    /**
     * 新增用户余额明细
     */
    @Operation(summary = "新增用户余额明细")
    @PostMapping("/save")
    public Result<?> add(@RequestBody BalanceDetails balanceDetails)
    {
        return Result.ok(balanceDetailsService.insertBalanceDetails(balanceDetails));
    }

    /**
     * 修改用户余额明细
     */
    @Operation(summary = "修改用户余额明细")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody BalanceDetails balanceDetails)
    {
        return Result.ok(balanceDetailsService.updateBalanceDetails(balanceDetails));
    }

    /**
     * 删除用户余额明细
     */
    @Operation(summary = "删除用户余额明细")
    @DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(balanceDetailsService.deleteBalanceDetailsByIds(ids));
    }
}
