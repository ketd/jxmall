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

import com.ketd.member.domain.Balance;
import com.ketd.member.service.IBalanceService;


/**
 * 用户余额表Controller
 *
 * @author ketd
 * @date 2024-06-04
 */
@Tag(name = "用户余额表Controller")
@RestController
@RequestMapping("/member/Balance")
public class BalanceController{

    @Autowired
    private IBalanceService balanceService;

    /**
     * 分页查询用户余额表列表
     */
    @Operation(summary ="分页查询用户余额表列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Balance> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Balance balance= pageRequest.getData();
        Page<Balance> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Balance> queryWrapper = new QueryWrapper<>(balance);

        IPage<Balance> balancePage = balanceService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(balancePage.getRecords(), balancePage.getTotal());



    }


    /**
     * 导出用户余额表列表
     */
    @Operation(summary = "导出用户余额表列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] balanceIds)
    {
        List<Balance> list = balanceService.listByIds(Arrays.asList(balanceIds));
            balanceService.export(list, response);

    }

    /**
     * 获取用户余额表详细信息
     */
    @Operation(summary = "获取用户余额表详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(balanceService.selectBalanceById(id));
    }


    @Operation(summary = "获取用户余额表详细信息")
    @GetMapping(value = "/getInfoByMemberId")
    public Result<?> getInfoByMemberId(@RequestParam("memberId") Long memberId)
    {
        return balanceService.getInfoByMemberId(memberId);
    }

    @Operation(summary = "获取用户余额")
    @GetMapping(value = "/getInfoByMember")
    public Result<?> getInfoByMember()
    {
        return balanceService.getInfoByMember();
    }

    /**
     * 新增用户余额表
     */
    @Operation(summary = "新增用户余额表")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Balance balance)
    {
        return Result.ok(balanceService.insertBalance(balance));
    }

    /**
     * 修改用户余额表
     */
    @Operation(summary = "修改用户余额表")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Balance balance)
    {
        return Result.ok(balanceService.updateBalance(balance));
    }

    /**
     * 删除用户余额表
     */
    @Operation(summary = "删除用户余额表")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(balanceService.deleteBalanceByIds(ids));
    }
}
