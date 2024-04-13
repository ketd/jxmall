package com.ketd.product.controller;




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

import com.ketd.common.BaseController;
import com.ketd.common.AjaxResult;
import com.ketd.common.TableDataInfo;
import com.ketd.common.domain.PageRequest;

import com.ketd.product.domain.CommentReplay;
import com.ketd.product.service.ICommentReplayService;


/**
 * 商品评价回复关系Controller
 *
 * @author ketd
 * @date 2024-04-12
 */
@Tag(name = "商品评价回复关系Controller")
@RestController
@RequestMapping("/product/CommentReplay")
public class CommentReplayController extends BaseController{

    @Autowired
    private ICommentReplayService commentReplayService;

    /**
     * 分页查询商品评价回复关系列表
     */
    @Operation(summary ="分页查询商品评价回复关系列表")
    @PostMapping("/list/page")
    public TableDataInfo list(@RequestBody PageRequest<CommentReplay> pageRequest)
    {

        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();

        // 执行条件查询
        CommentReplay commentReplay= pageRequest.getData();
        Page<CommentReplay> page = new Page<>(pageNum, pageSize);
        QueryWrapper<CommentReplay> queryWrapper = new QueryWrapper<>(commentReplay);

        IPage<CommentReplay> commentReplayPage = commentReplayService.page(page, queryWrapper);
        return getDataTable(commentReplayPage.getRecords(), commentReplayPage.getTotal());



    }


    /**
     * 导出商品评价回复关系列表
     */
    @Operation(summary = "导出商品评价回复关系列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Long[] commentReplayIds)
    {
        List<CommentReplay> list = commentReplayService.listByIds(Arrays.asList(commentReplayIds));
            commentReplayService.export(list, response);

    }

    /**
     * 获取商品评价回复关系详细信息
     */
    @Operation(summary = "获取商品评价回复关系详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(@RequestParam("id") Long id)
    {
        return success(commentReplayService.selectCommentReplayById(id));
    }

    /**
     * 新增商品评价回复关系
     */
    @Operation(summary = "新增商品评价回复关系")
    @PostMapping("/save")
    public AjaxResult add(@RequestBody CommentReplay commentReplay)
    {
        return toAjax(commentReplayService.insertCommentReplay(commentReplay));
    }

    /**
     * 修改商品评价回复关系
     */
    @Operation(summary = "修改商品评价回复关系")
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody CommentReplay commentReplay)
    {
        return toAjax(commentReplayService.updateCommentReplay(commentReplay));
    }

    /**
     * 删除商品评价回复关系
     */
    @Operation(summary = "删除商品评价回复关系")
	@DeleteMapping("/delete")
    public AjaxResult remove(@RequestBody Long[] ids)
    {
        return toAjax(commentReplayService.deleteCommentReplayByIds(ids));
    }
}
