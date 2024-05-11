package com.ketd.coupon.controller;




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

import com.ketd.coupon.domain.MemberPrice;
import com.ketd.coupon.service.IMemberPriceService;


/**
 * 商品会员价格Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "商品会员价格Controller")
@RestController
@RequestMapping("/coupon/MemberPrice")
public class MemberPriceController{

    @Autowired
    private IMemberPriceService memberPriceService;

    /**
     * 分页查询商品会员价格列表
     */
    @Operation(summary ="分页查询商品会员价格列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberPrice> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberPrice memberPrice= pageRequest.getData();
        Page<MemberPrice> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberPrice> queryWrapper = new QueryWrapper<>(memberPrice);

        IPage<MemberPrice> memberPricePage = memberPriceService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberPricePage.getRecords(), memberPricePage.getTotal());



    }


    /**
     * 导出商品会员价格列表
     */
    @Operation(summary = "导出商品会员价格列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberPriceIds)
    {
        List<MemberPrice> list = memberPriceService.listByIds(Arrays.asList(memberPriceIds));
            memberPriceService.export(list, response);

    }

    /**
     * 获取商品会员价格详细信息
     */
    @Operation(summary = "获取商品会员价格详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberPriceService.selectMemberPriceById(id));
    }

    /**
     * 新增商品会员价格
     */
    @Operation(summary = "新增商品会员价格")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberPrice memberPrice)
    {
        return Result.ok(memberPriceService.insertMemberPrice(memberPrice));
    }

    /**
     * 修改商品会员价格
     */
    @Operation(summary = "修改商品会员价格")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberPrice memberPrice)
    {
        return Result.ok(memberPriceService.updateMemberPrice(memberPrice));
    }

    /**
     * 删除商品会员价格
     */
    @Operation(summary = "删除商品会员价格")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberPriceService.deleteMemberPriceByIds(ids));
    }
}
