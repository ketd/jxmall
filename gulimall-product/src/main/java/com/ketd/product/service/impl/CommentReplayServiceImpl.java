package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.CommentReplayMapper;
import com.ketd.product.domain.CommentReplay;
import com.ketd.product.service.ICommentReplayService;


/**
 * 商品评价回复关系Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplay> implements ICommentReplayService {

    @Autowired
    private CommentReplayMapper commentReplayMapper;



    /**
     * 查询商品评价回复关系
     *
     * @param id 商品评价回复关系主键
     * @return 商品评价回复关系
     */
    @Override
    public CommentReplay selectCommentReplayById(Long id)
    {
        return commentReplayMapper.selectById(id);
    }



    /**
     * 查询商品评价回复关系列表
     *
     * @param commentReplay 商品评价回复关系
     * @return 商品评价回复关系
     */
    @Override
    public List<CommentReplay> selectCommentReplayList(CommentReplay commentReplay)
    {
        QueryWrapper<CommentReplay> queryWrapper = new QueryWrapper<>(commentReplay);
        return commentReplayMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品评价回复关系
     *
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */

    @Override
    public int insertCommentReplay(CommentReplay commentReplay) {
        return commentReplayMapper.insert(commentReplay);
    }





    /**
     * 修改商品评价回复关系
     *
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */

    @Override
    public int updateCommentReplay(CommentReplay commentReplay) {
        return commentReplayMapper.updateById(commentReplay);
    }

    /**
     * 批量删除商品评价回复关系
     *
     * @param ids 需要删除的商品评价回复关系主键集合
     * @return 结果
     */
    @Override
    public int deleteCommentReplayByIds(Long[] ids) {
        return commentReplayMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品评价回复关系信息
     *
     * @param id 商品评价回复关系主键
     * @return 结果
     */
    @Override
    public int deleteCommentReplayById(Long id) {
        return commentReplayMapper.deleteById(id);
    }


    /**
     * 导出商品评价回复关系列表
     */
    @Override
    public void export(List<CommentReplay> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            EasyExcel.write(response.getOutputStream(), CommentReplay.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}