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

import com.ketd.coupon.domain.HomeSubjectSpu;
import com.ketd.coupon.service.IHomeSubjectSpuService;


/**
 * 专题商品Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "专题商品Controller")
@RestController
@RequestMapping("/coupon/HomeSubjectSpu")
public class HomeSubjectSpuController{

    @Autowired
    private IHomeSubjectSpuService homeSubjectSpuService;

    /**
     * 分页查询专题商品列表
     */
    @Operation(summary ="分页查询专题商品列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeSubjectSpu> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        HomeSubjectSpu homeSubjectSpu= pageRequest.getData();
        Page<HomeSubjectSpu> page = new Page<>(pageNum, pageSize);
        QueryWrapper<HomeSubjectSpu> queryWrapper = new QueryWrapper<>(homeSubjectSpu);

        IPage<HomeSubjectSpu> homeSubjectSpuPage = homeSubjectSpuService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(homeSubjectSpuPage.getRecords(), homeSubjectSpuPage.getTotal());



    }


    /**
     * 导出专题商品列表
     */
    @Operation(summary = "导出专题商品列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] homeSubjectSpuIds)
    {
        List<HomeSubjectSpu> list = homeSubjectSpuService.listByIds(Arrays.asList(homeSubjectSpuIds));
            homeSubjectSpuService.export(list, response);

    }

    /**
     * 获取专题商品详细信息
     */
    @Operation(summary = "获取专题商品详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(homeSubjectSpuService.selectHomeSubjectSpuById(id));
    }

    /**
     * 新增专题商品
     */
    @Operation(summary = "新增专题商品")
    @PostMapping("/save")
    public Result<?> add(@RequestBody HomeSubjectSpu homeSubjectSpu)
    {
        return Result.ok(homeSubjectSpuService.insertHomeSubjectSpu(homeSubjectSpu));
    }

    /**
     * 修改专题商品
     */
    @Operation(summary = "修改专题商品")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody HomeSubjectSpu homeSubjectSpu)
    {
        return Result.ok(homeSubjectSpuService.updateHomeSubjectSpu(homeSubjectSpu));
    }

    /**
     * 删除专题商品
     */
    @Operation(summary = "删除专题商品")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(homeSubjectSpuService.deleteHomeSubjectSpuByIds(ids));
    }
}
