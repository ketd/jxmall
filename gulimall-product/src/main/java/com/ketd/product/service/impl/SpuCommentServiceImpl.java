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
import com.ketd.product.mapper.SpuCommentMapper;
import com.ketd.product.domain.SpuComment;
import com.ketd.product.service.ISpuCommentService;


/**
 * 商品评价Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper, SpuComment> implements ISpuCommentService {

    @Autowired
    private SpuCommentMapper spuCommentMapper;



    /**
     * 查询商品评价
     *
     * @param id 商品评价主键
     * @return 商品评价
     */
    @Override
    public SpuComment selectSpuCommentById(Long id)
    {
        return spuCommentMapper.selectById(id);
    }



    /**
     * 查询商品评价列表
     *
     * @param spuComment 商品评价
     * @return 商品评价
     */
    @Override
    public List<SpuComment> selectSpuCommentList(SpuComment spuComment)
    {
        QueryWrapper<SpuComment> queryWrapper = new QueryWrapper<>(spuComment);
        return spuCommentMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品评价
     *
     * @param spuComment 商品评价
     * @return 结果
     */

    @Override
    public int insertSpuComment(SpuComment spuComment) {
        return spuCommentMapper.insert(spuComment);
    }





    /**
     * 修改商品评价
     *
     * @param spuComment 商品评价
     * @return 结果
     */

    @Override
    public int updateSpuComment(SpuComment spuComment) {
        return spuCommentMapper.updateById(spuComment);
    }

    /**
     * 批量删除商品评价
     *
     * @param ids 需要删除的商品评价主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuCommentByIds(Long[] ids) {
        return spuCommentMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品评价信息
     *
     * @param id 商品评价主键
     * @return 结果
     */
    @Override
    public int deleteSpuCommentById(Long id) {
        return spuCommentMapper.deleteById(id);
    }


    /**
     * 导出商品评价列表
     */
    @Override
    public void export(List<SpuComment> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SpuComment.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}