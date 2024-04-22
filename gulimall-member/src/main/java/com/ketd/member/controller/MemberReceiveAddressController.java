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

import com.ketd.member.domain.MemberReceiveAddress;
import com.ketd.member.service.IMemberReceiveAddressService;


/**
 * 会员收货地址Controller
 *
 * @author ketd
 * @date 2024-04-18
 */
@Tag(name = "会员收货地址Controller")
@RestController
@RequestMapping("/member/MemberReceiveAddress")
public class MemberReceiveAddressController{

    @Autowired
    private IMemberReceiveAddressService memberReceiveAddressService;

    /**
     * 分页查询会员收货地址列表
     */
    @Operation(summary ="分页查询会员收货地址列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<MemberReceiveAddress> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        MemberReceiveAddress memberReceiveAddress= pageRequest.getData();
        Page<MemberReceiveAddress> page = new Page<>(pageNum, pageSize);
        QueryWrapper<MemberReceiveAddress> queryWrapper = new QueryWrapper<>(memberReceiveAddress);

        IPage<MemberReceiveAddress> memberReceiveAddressPage = memberReceiveAddressService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(memberReceiveAddressPage.getRecords(), memberReceiveAddressPage.getTotal());



    }


    /**
     * 导出会员收货地址列表
     */
    @Operation(summary = "导出会员收货地址列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] memberReceiveAddressIds)
    {
        List<MemberReceiveAddress> list = memberReceiveAddressService.listByIds(Arrays.asList(memberReceiveAddressIds));
            memberReceiveAddressService.export(list, response);

    }

    /**
     * 获取会员收货地址详细信息
     */
    @Operation(summary = "获取会员收货地址详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(memberReceiveAddressService.selectMemberReceiveAddressById(id));
    }

    /**
     * 新增会员收货地址
     */
    @Operation(summary = "新增会员收货地址")
    @PostMapping("/save")
    public Result<?> add(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return Result.ok(memberReceiveAddressService.insertMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 修改会员收货地址
     */
    @Operation(summary = "修改会员收货地址")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody MemberReceiveAddress memberReceiveAddress)
    {
        return Result.ok(memberReceiveAddressService.updateMemberReceiveAddress(memberReceiveAddress));
    }

    /**
     * 删除会员收货地址
     */
    @Operation(summary = "删除会员收货地址")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(memberReceiveAddressService.deleteMemberReceiveAddressByIds(ids));
    }
}
