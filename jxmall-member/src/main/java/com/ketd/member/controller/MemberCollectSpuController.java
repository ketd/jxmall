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

import com.ketd.member.domain.MemberCollectSpu;
import com.ketd.member.service.IMemberCollectSpuService;


/**
 * 会员收藏的商品Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员收藏的商品Controller")
@RestController
@RequestMapping("/member/MemberCollectSpu")
public class MemberCollectSpuController{

    @Autowired
    private IMemberCollectSpuService memberCollectSpuService;

    /**
     * 分页查询会员收藏的商品列表
     */
    @Operation(summary ="分页查询会员收藏的商品列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberCollectSpu> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberCollectSpu memberCollectSpu= pageRequest.getData();
        Page<MemberCollectSpu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberCollectSpu> queryWrapper = new QueryWrapper<>(memberCollectSpu);

        IPage<MemberCollectSpu> memberCollectSpuPage = memberCollectSpuService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberCollectSpuPage.getRecords(), memberCollectSpuPage.getTotal());



    }


    /**
     * 导出会员收藏的商品列表
     */
    @Operation(summary = "导出会员收藏的商品列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberCollectSpuIds)
    {
        List<MemberCollectSpu> list = memberCollectSpuService.listByIds(Arrays.asList(memberCollectSpuIds));
            memberCollectSpuService.export(list, response);

    }

    /**
     * 获取会员收藏的商品详细信息
     */
    @Operation(summary = "获取会员收藏的商品详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberCollectSpuService.selectMemberCollectSpuById(id));
    }

    /**
     * 新增会员收藏的商品
     */
    @Operation(summary = "新增会员收藏的商品")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return Result.ok(memberCollectSpuService.insertMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 修改会员收藏的商品
     */
    @Operation(summary = "修改会员收藏的商品")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberCollectSpu memberCollectSpu)
    {
        return Result.ok(memberCollectSpuService.updateMemberCollectSpu(memberCollectSpu));
    }

    /**
     * 删除会员收藏的商品
     */
    @Operation(summary = "删除会员收藏的商品")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberCollectSpuService.deleteMemberCollectSpuByIds(ids));
    }
}
