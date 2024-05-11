package com.ketd.member.controller;




import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ketd.member.vo.MemberVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ketd.common.result.Result;
import com.ketd.common.domain.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.member.domain.Member;
import com.ketd.member.service.IMemberService;


/**
 * 会员Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员Controller")
@RestController
@RequestMapping("/member/Member")
public class MemberController{

    @Autowired
    private IMemberService memberService;

    /**
     * 分页查询会员列表
     */
    @Operation(summary ="分页查询会员列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<Member> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        Member member= pageRequest.getData();
        Page<Member> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(member);

        IPage<Member> memberPage = memberService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberPage.getRecords(), memberPage.getTotal());



    }



    @Operation(summary = "用户注册控制器")
    @PostMapping()
    public Result<?> regist(@RequestBody MemberVo memberVo) {//json数据接收加@RequestBody注解
        return memberService.regist(memberVo);
    }

    @Operation(summary = "获取注册验证码")
    @PostMapping(value = "/getEmailCode")
    public CompletableFuture<Result<?>> getEmailCode(@RequestParam(value = "email") String email) {

        return memberService.sendMailCode(email)
                .thenApply(result -> result);
    }




    /**
     * 导出会员列表
     */
    @Operation(summary = "导出会员列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberIds)
    {
        List<Member> list = memberService.listByIds(Arrays.asList(memberIds));
            memberService.export(list, response);

    }

    /**
     * 获取会员详细信息
     */
    @Operation(summary = "获取会员详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberService.selectMemberById(id));
    }

    /**
     * 新增会员
     */
    @Operation(summary = "新增会员")
    @PostMapping("/save")
    public Result<?> add(@RequestBody Member member)
    {
        return Result.ok(memberService.insertMember(member));
    }

    /**
     * 修改会员
     */
    @Operation(summary = "修改会员")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody Member member)
    {
        return Result.ok(memberService.updateMember(member));
    }

    /**
     * 删除会员
     */
    @Operation(summary = "删除会员")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberService.deleteMemberByIds(ids));
    }
}
