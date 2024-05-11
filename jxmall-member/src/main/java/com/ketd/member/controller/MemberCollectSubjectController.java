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

import com.ketd.member.domain.MemberCollectSubject;
import com.ketd.member.service.IMemberCollectSubjectService;


/**
 * 会员收藏的专题活动Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员收藏的专题活动Controller")
@RestController
@RequestMapping("/member/MemberCollectSubject")
public class MemberCollectSubjectController{

    @Autowired
    private IMemberCollectSubjectService memberCollectSubjectService;

    /**
     * 分页查询会员收藏的专题活动列表
     */
    @Operation(summary ="分页查询会员收藏的专题活动列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberCollectSubject> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberCollectSubject memberCollectSubject= pageRequest.getData();
        Page<MemberCollectSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberCollectSubject> queryWrapper = new QueryWrapper<>(memberCollectSubject);

        IPage<MemberCollectSubject> memberCollectSubjectPage = memberCollectSubjectService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberCollectSubjectPage.getRecords(), memberCollectSubjectPage.getTotal());



    }


    /**
     * 导出会员收藏的专题活动列表
     */
    @Operation(summary = "导出会员收藏的专题活动列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberCollectSubjectIds)
    {
        List<MemberCollectSubject> list = memberCollectSubjectService.listByIds(Arrays.asList(memberCollectSubjectIds));
            memberCollectSubjectService.export(list, response);

    }

    /**
     * 获取会员收藏的专题活动详细信息
     */
    @Operation(summary = "获取会员收藏的专题活动详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberCollectSubjectService.selectMemberCollectSubjectById(id));
    }

    /**
     * 新增会员收藏的专题活动
     */
    @Operation(summary = "新增会员收藏的专题活动")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return Result.ok(memberCollectSubjectService.insertMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 修改会员收藏的专题活动
     */
    @Operation(summary = "修改会员收藏的专题活动")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberCollectSubject memberCollectSubject)
    {
        return Result.ok(memberCollectSubjectService.updateMemberCollectSubject(memberCollectSubject));
    }

    /**
     * 删除会员收藏的专题活动
     */
    @Operation(summary = "删除会员收藏的专题活动")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberCollectSubjectService.deleteMemberCollectSubjectByIds(ids));
    }
}
