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

import com.ketd.member.domain.MemberLoginLog;
import com.ketd.member.service.IMemberLoginLogService;


/**
 * 会员登录记录Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员登录记录Controller")
@RestController
@RequestMapping("/member/MemberLoginLog")
public class MemberLoginLogController{

    @Autowired
    private IMemberLoginLogService memberLoginLogService;

    /**
     * 分页查询会员登录记录列表
     */
    @Operation(summary ="分页查询会员登录记录列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberLoginLog> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberLoginLog memberLoginLog= pageRequest.getData();
        Page<MemberLoginLog> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberLoginLog> queryWrapper = new QueryWrapper<>(memberLoginLog);

        IPage<MemberLoginLog> memberLoginLogPage = memberLoginLogService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberLoginLogPage.getRecords(), memberLoginLogPage.getTotal());



    }


    /**
     * 导出会员登录记录列表
     */
    @Operation(summary = "导出会员登录记录列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberLoginLogIds)
    {
        List<MemberLoginLog> list = memberLoginLogService.listByIds(Arrays.asList(memberLoginLogIds));
            memberLoginLogService.export(list, response);

    }

    /**
     * 获取会员登录记录详细信息
     */
    @Operation(summary = "获取会员登录记录详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberLoginLogService.selectMemberLoginLogById(id));
    }

    /**
     * 新增会员登录记录
     */
    @Operation(summary = "新增会员登录记录")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberLoginLog memberLoginLog)
    {
        return Result.ok(memberLoginLogService.insertMemberLoginLog(memberLoginLog));
    }

    /**
     * 修改会员登录记录
     */
    @Operation(summary = "修改会员登录记录")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberLoginLog memberLoginLog)
    {
        return Result.ok(memberLoginLogService.updateMemberLoginLog(memberLoginLog));
    }

    /**
     * 删除会员登录记录
     */
    @Operation(summary = "删除会员登录记录")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberLoginLogService.deleteMemberLoginLogByIds(ids));
    }
}
