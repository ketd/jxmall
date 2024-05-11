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

import com.ketd.coupon.domain.HomeSubject;
import com.ketd.coupon.service.IHomeSubjectService;


/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Controller
 *
 * @author ketd
 * @date 2024-04-20
 */
@Tag(name = "首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Controller")
@RestController
@RequestMapping("/coupon/HomeSubject")
public class HomeSubjectController{

    @Autowired
    private IHomeSubjectService homeSubjectService;

    /**
     * 分页查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     */
    @Operation(summary ="分页查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<HomeSubject> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        HomeSubject homeSubject= pageRequest.getData();
        Page<HomeSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper<HomeSubject> queryWrapper = new QueryWrapper<>(homeSubject);

        IPage<HomeSubject> homeSubjectPage = homeSubjectService.page(page, queryWrapper);
        return TableDataInfo.getDataTable(homeSubjectPage.getRecords(), homeSubjectPage.getTotal());



    }


    /**
     * 导出首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     */
    @Operation(summary = "导出首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] homeSubjectIds)
    {
        List<HomeSubject> list = homeSubjectService.listByIds(Arrays.asList(homeSubjectIds));
            homeSubjectService.export(list, response);

    }

    /**
     * 获取首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详细信息
     */
    @Operation(summary = "获取首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详细信息")
    @GetMapping(value = "/info")
    public Result<?> getInfo(@RequestParam("id") Long id)
    {
        return Result.ok(homeSubjectService.selectHomeSubjectById(id));
    }

    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Operation(summary = "新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
    @PostMapping("/save")
    public Result<?> add(@RequestBody HomeSubject homeSubject)
    {
        return Result.ok(homeSubjectService.insertHomeSubject(homeSubject));
    }

    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Operation(summary = "修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
    @PutMapping("/update")
    public Result<?> edit(@RequestBody HomeSubject homeSubject)
    {
        return Result.ok(homeSubjectService.updateHomeSubject(homeSubject));
    }

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Operation(summary = "删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
	@DeleteMapping("/delete")
    public Result<?> remove(@RequestBody Long[] ids)
    {
        return Result.ok(homeSubjectService.deleteHomeSubjectByIds(ids));
    }
}
