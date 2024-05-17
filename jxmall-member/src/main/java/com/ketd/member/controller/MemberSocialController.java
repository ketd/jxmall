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

import com.ketd.member.domain.MemberSocial;
import com.ketd.member.service.IMemberSocialService;


/**
 * 单点登录用户信息关联Controller
 *
 * @author ketd
 * @date 2024-05-17
 */
@Tag(name = "单点登录用户信息关联Controller")
@RestController
@RequestMapping("/member/MemberSocial")
public class MemberSocialController{

    @Autowired
    private IMemberSocialService memberSocialService;

    /**
     * 分页查询单点登录用户信息关联列表
     */
    @Operation(summary ="分页查询单点登录用户信息关联列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberSocial> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberSocial memberSocial= pageRequest.getData();
        Page<MemberSocial> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberSocial> queryWrapper = new QueryWrapper<>(memberSocial);

        IPage<MemberSocial> memberSocialPage = memberSocialService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberSocialPage.getRecords(), memberSocialPage.getTotal());



    }


    /**
     * 导出单点登录用户信息关联列表
     */
    @Operation(summary = "导出单点登录用户信息关联列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberSocialIds)
    {
        List<MemberSocial> list = memberSocialService.listByIds(Arrays.asList(memberSocialIds));
            memberSocialService.export(list, response);

    }

    /**
     * 获取单点登录用户信息关联详细信息
     */
    @Operation(summary = "获取单点登录用户信息关联详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("memberId") Long memberId)
    {
        return Result.ok(memberSocialService.selectMemberSocialByMemberId(memberId));
    }

    /**
     * 获取单点登录用户信息关联详细信息
     */
    @Operation(summary = "获取单点登录用户信息关联详细信息")
    @GetMapping(value = "/info/socialUid")
    public Result<?> getInfoBySocialUid(@RequestParam("socialUid") String socialUid,@RequestParam("type")Integer Type)
    {
        return memberSocialService.selectMemberSocialBygetInfoBySocialUid(socialUid,Type);
    }

    /**
     * 新增单点登录用户信息关联
     */
    @Operation(summary = "新增单点登录用户信息关联")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberSocial memberSocial)
    {
        return Result.ok(memberSocialService.insertMemberSocial(memberSocial));
    }

    /**
     * 修改单点登录用户信息关联
     */
    @Operation(summary = "修改单点登录用户信息关联")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberSocial memberSocial)
    {
        return Result.ok(memberSocialService.updateMemberSocial(memberSocial));
    }

    /**
     * 删除单点登录用户信息关联
     */
    @Operation(summary = "删除单点登录用户信息关联")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] memberIds)
    {
        return Result.ok(memberSocialService.deleteMemberSocialByMemberIds(memberIds));
    }
}
