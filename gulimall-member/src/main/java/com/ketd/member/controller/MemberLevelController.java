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

import com.ketd.member.domain.MemberLevel;
import com.ketd.member.service.IMemberLevelService;


/**
 * 会员等级Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员等级Controller")
@RestController
@RequestMapping("/member/MemberLevel")
public class MemberLevelController{

    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 分页查询会员等级列表
     */
    @Operation(summary ="分页查询会员等级列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberLevel> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberLevel memberLevel= pageRequest.getData();
        Page<MemberLevel> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>(memberLevel);

        IPage<MemberLevel> memberLevelPage = memberLevelService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberLevelPage.getRecords(), memberLevelPage.getTotal());



    }


    /**
     * 导出会员等级列表
     */
    @Operation(summary = "导出会员等级列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberLevelIds)
    {
        List<MemberLevel> list = memberLevelService.listByIds(Arrays.asList(memberLevelIds));
            memberLevelService.export(list, response);

    }

    /**
     * 获取会员等级详细信息
     */
    @Operation(summary = "获取会员等级详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberLevelService.selectMemberLevelById(id));
    }

    /**
     * 新增会员等级
     */
    @Operation(summary = "新增会员等级")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberLevel memberLevel)
    {
        return Result.ok(memberLevelService.insertMemberLevel(memberLevel));
    }

    /**
     * 修改会员等级
     */
    @Operation(summary = "修改会员等级")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberLevel memberLevel)
    {
        return Result.ok(memberLevelService.updateMemberLevel(memberLevel));
    }

    /**
     * 删除会员等级
     */
    @Operation(summary = "删除会员等级")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberLevelService.deleteMemberLevelByIds(ids));
    }
}
