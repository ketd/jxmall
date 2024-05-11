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
import com.ketd.product.mapper.AttrAttrgroupRelationMapper;
import com.ketd.product.domain.AttrAttrgroupRelation;
import com.ketd.product.service.IAttrAttrgroupRelationService;


/**
 * 属性&属性分组关联Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelation> implements IAttrAttrgroupRelationService {

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;



    /**
     * 查询属性&属性分组关联
     *
     * @param id 属性&属性分组关联主键
     * @return 属性&属性分组关联
     */
    @Override
    public AttrAttrgroupRelation selectAttrAttrgroupRelationById(Long id)
    {
        return attrAttrgroupRelationMapper.selectById(id);
    }



    /**
     * 查询属性&属性分组关联列表
     *
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 属性&属性分组关联
     */
    @Override
    public List<AttrAttrgroupRelation> selectAttrAttrgroupRelationList(AttrAttrgroupRelation attrAttrgroupRelation)
    {
        QueryWrapper<AttrAttrgroupRelation> queryWrapper = new QueryWrapper<>(attrAttrgroupRelation);
        return attrAttrgroupRelationMapper.selectList(queryWrapper);
    }

    /**
     * 新增属性&属性分组关联
     *
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */

    @Override
    public int insertAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation) {
        return attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
    }





    /**
     * 修改属性&属性分组关联
     *
     * @param attrAttrgroupRelation 属性&属性分组关联
     * @return 结果
     */

    @Override
    public int updateAttrAttrgroupRelation(AttrAttrgroupRelation attrAttrgroupRelation) {
        return attrAttrgroupRelationMapper.updateById(attrAttrgroupRelation);
    }

    /**
     * 批量删除属性&属性分组关联
     *
     * @param ids 需要删除的属性&属性分组关联主键集合
     * @return 结果
     */
    @Override
    public int deleteAttrAttrgroupRelationByIds(Long[] ids) {
        return attrAttrgroupRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除属性&属性分组关联信息
     *
     * @param id 属性&属性分组关联主键
     * @return 结果
     */
    @Override
    public int deleteAttrAttrgroupRelationById(Long id) {
        return attrAttrgroupRelationMapper.deleteById(id);
    }


    /**
     * 导出属性&属性分组关联列表
     */
    @Override
    public void export(List<AttrAttrgroupRelation> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), AttrAttrgroupRelation.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}