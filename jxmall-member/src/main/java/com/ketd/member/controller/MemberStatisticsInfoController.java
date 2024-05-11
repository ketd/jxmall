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

import com.ketd.member.domain.MemberStatisticsInfo;
import com.ketd.member.service.IMemberStatisticsInfoService;


/**
 * 会员统计信息Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员统计信息Controller")
@RestController
@RequestMapping("/member/MemberStatisticsInfo")
public class MemberStatisticsInfoController{

    @Autowired
    private IMemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 分页查询会员统计信息列表
     */
    @Operation(summary ="分页查询会员统计信息列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberStatisticsInfo> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberStatisticsInfo memberStatisticsInfo= pageRequest.getData();
        Page<MemberStatisticsInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberStatisticsInfo> queryWrapper = new QueryWrapper<>(memberStatisticsInfo);

        IPage<MemberStatisticsInfo> memberStatisticsInfoPage = memberStatisticsInfoService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberStatisticsInfoPage.getRecords(), memberStatisticsInfoPage.getTotal());



    }


    /**
     * 导出会员统计信息列表
     */
    @Operation(summary = "导出会员统计信息列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberStatisticsInfoIds)
    {
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.listByIds(Arrays.asList(memberStatisticsInfoIds));
            memberStatisticsInfoService.export(list, response);

    }

    /**
     * 获取会员统计信息详细信息
     */
    @Operation(summary = "获取会员统计信息详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberStatisticsInfoService.selectMemberStatisticsInfoById(id));
    }

    /**
     * 新增会员统计信息
     */
    @Operation(summary = "新增会员统计信息")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return Result.ok(memberStatisticsInfoService.insertMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 修改会员统计信息
     */
    @Operation(summary = "修改会员统计信息")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return Result.ok(memberStatisticsInfoService.updateMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 删除会员统计信息
     */
    @Operation(summary = "删除会员统计信息")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberStatisticsInfoService.deleteMemberStatisticsInfoByIds(ids));
    }
}
