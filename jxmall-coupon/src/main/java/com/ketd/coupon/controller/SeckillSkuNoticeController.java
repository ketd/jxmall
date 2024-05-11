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

import com.ketd.coupon.domain.SeckillSkuNotice;
import com.ketd.coupon.service.ISeckillSkuNoticeService;


/**
 * 秒杀商品通知订阅Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "秒杀商品通知订阅Controller")
@RestController
@RequestMapping("/coupon/SeckillSkuNotice")
public class SeckillSkuNoticeController{

    @Autowired
    private ISeckillSkuNoticeService seckillSkuNoticeService;

    /**
     * 分页查询秒杀商品通知订阅列表
     */
    @Operation(summary ="分页查询秒杀商品通知订阅列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSkuNotice> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SeckillSkuNotice seckillSkuNotice= pageRequest.getData();
        Page<SeckillSkuNotice> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SeckillSkuNotice> queryWrapper = new QueryWrapper<>(seckillSkuNotice);

        IPage<SeckillSkuNotice> seckillSkuNoticePage = seckillSkuNoticeService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(seckillSkuNoticePage.getRecords(), seckillSkuNoticePage.getTotal());



    }


    /**
     * 导出秒杀商品通知订阅列表
     */
    @Operation(summary = "导出秒杀商品通知订阅列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] seckillSkuNoticeIds)
    {
        List<SeckillSkuNotice> list = seckillSkuNoticeService.listByIds(Arrays.asList(seckillSkuNoticeIds));
            seckillSkuNoticeService.export(list, response);

    }

    /**
     * 获取秒杀商品通知订阅详细信息
     */
    @Operation(summary = "获取秒杀商品通知订阅详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(seckillSkuNoticeService.selectSeckillSkuNoticeById(id));
    }

    /**
     * 新增秒杀商品通知订阅
     */
    @Operation(summary = "新增秒杀商品通知订阅")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SeckillSkuNotice seckillSkuNotice)
    {
        return Result.ok(seckillSkuNoticeService.insertSeckillSkuNotice(seckillSkuNotice));
    }

    /**
     * 修改秒杀商品通知订阅
     */
    @Operation(summary = "修改秒杀商品通知订阅")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SeckillSkuNotice seckillSkuNotice)
    {
        return Result.ok(seckillSkuNoticeService.updateSeckillSkuNotice(seckillSkuNotice));
    }

    /**
     * 删除秒杀商品通知订阅
     */
    @Operation(summary = "删除秒杀商品通知订阅")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(seckillSkuNoticeService.deleteSeckillSkuNoticeByIds(ids));
    }
}
