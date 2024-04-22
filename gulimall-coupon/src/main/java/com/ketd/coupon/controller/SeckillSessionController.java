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

import com.ketd.coupon.domain.SeckillSession;
import com.ketd.coupon.service.ISeckillSessionService;


/**
 * 秒杀活动场次Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "秒杀活动场次Controller")
@RestController
@RequestMapping("/coupon/SeckillSession")
public class SeckillSessionController{

    @Autowired
    private ISeckillSessionService seckillSessionService;

    /**
     * 分页查询秒杀活动场次列表
     */
    @Operation(summary ="分页查询秒杀活动场次列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<SeckillSession> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        SeckillSession seckillSession= pageRequest.getData();
        Page<SeckillSession> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SeckillSession> queryWrapper = new QueryWrapper<>(seckillSession);

        IPage<SeckillSession> seckillSessionPage = seckillSessionService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(seckillSessionPage.getRecords(), seckillSessionPage.getTotal());



    }


    /**
     * 导出秒杀活动场次列表
     */
    @Operation(summary = "导出秒杀活动场次列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] seckillSessionIds)
    {
        List<SeckillSession> list = seckillSessionService.listByIds(Arrays.asList(seckillSessionIds));
            seckillSessionService.export(list, response);

    }

    /**
     * 获取秒杀活动场次详细信息
     */
    @Operation(summary = "获取秒杀活动场次详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(seckillSessionService.selectSeckillSessionById(id));
    }

    /**
     * 新增秒杀活动场次
     */
    @Operation(summary = "新增秒杀活动场次")
    @PostMapping("/save")
    public Result<?> add(@RequestBody SeckillSession seckillSession)
    {
        return Result.ok(seckillSessionService.insertSeckillSession(seckillSession));
    }

    /**
     * 修改秒杀活动场次
     */
    @Operation(summary = "修改秒杀活动场次")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody SeckillSession seckillSession)
    {
        return Result.ok(seckillSessionService.updateSeckillSession(seckillSession));
    }

    /**
     * 删除秒杀活动场次
     */
    @Operation(summary = "删除秒杀活动场次")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(seckillSessionService.deleteSeckillSessionByIds(ids));
    }
}
