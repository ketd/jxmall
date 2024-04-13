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
import com.ketd.product.mapper.AttrMapper;
import com.ketd.product.domain.Attr;
import com.ketd.product.service.IAttrService;


/**
 * 商品属性Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    private AttrMapper attrMapper;



    /**
     * 查询商品属性
     *
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    @Override
    public Attr selectAttrByAttrId(Long attrId)
    {
        return attrMapper.selectById(attrId);
    }



    /**
     * 查询商品属性列表
     *
     * @param attr 商品属性
     * @return 商品属性
     */
    @Override
    public List<Attr> selectAttrList(Attr attr)
    {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>(attr);
        return attrMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品属性
     *
     * @param attr 商品属性
     * @return 结果
     */

    @Override
    public int insertAttr(Attr attr) {
        return attrMapper.insert(attr);
    }





    /**
     * 修改商品属性
     *
     * @param attr 商品属性
     * @return 结果
     */

    @Override
    public int updateAttr(Attr attr) {
        return attrMapper.updateById(attr);
    }

    /**
     * 批量删除商品属性
     *
     * @param attrIds 需要删除的商品属性主键集合
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrIds(Long[] attrIds) {
        return attrMapper.deleteBatchIds(Arrays.asList(attrIds));
    }

    /**
     * 删除商品属性信息
     *
     * @param attrId 商品属性主键
     * @return 结果
     */
    @Override
    public int deleteAttrByAttrId(Long attrId) {
        return attrMapper.deleteById(attrId);
    }


    /**
     * 导出商品属性列表
     */
    @Override
    public void export(List<Attr> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), Attr.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}