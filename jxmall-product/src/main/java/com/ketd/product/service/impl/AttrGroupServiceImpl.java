package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.domain.PageRequest;
import com.ketd.common.result.Result;
import com.ketd.product.domain.Attr;
import com.ketd.product.mapper.AttrMapper;
import com.ketd.product.vo.AttrGroupWithAttrsVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.AttrGroupMapper;
import com.ketd.product.domain.AttrGroup;
import com.ketd.product.service.IAttrGroupService;


/**
 * 属性分组Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements IAttrGroupService {

    @Autowired
    private AttrGroupMapper attrGroupMapper;

    @Autowired
    private AttrMapper  attrMapper;

    @Autowired
    private  AttrServiceImpl  attrService;


    /**
     * 查询属性分组
     *
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    @Override
    public AttrGroup selectAttrGroupByAttrGroupId(Long attrGroupId) {
        return attrGroupMapper.selectById(attrGroupId);
    }


    /**
     * 查询属性分组列表
     *
     * @param attrGroup 属性分组
     * @return 属性分组
     */
    @Override
    public List<AttrGroup> selectAttrGroupList(AttrGroup attrGroup) {
        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>(attrGroup);
        return attrGroupMapper.selectList(queryWrapper);
    }

    /**
     * 新增属性分组
     *
     * @param attrGroup 属性分组
     * @return 结果
     */

    @Override
    public int insertAttrGroup(AttrGroup attrGroup) {
        return attrGroupMapper.insert(attrGroup);
    }


    /**
     * 修改属性分组
     *
     * @param attrGroup 属性分组
     * @return 结果
     */

    @Override
    public int updateAttrGroup(AttrGroup attrGroup) {
        return attrGroupMapper.updateById(attrGroup);
    }

    /**
     * 批量删除属性分组
     *
     * @param attrGroupIds 需要删除的属性分组主键集合
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds) {
        return attrGroupMapper.deleteBatchIds(Arrays.asList(attrGroupIds));
    }

    /**
     * 删除属性分组信息
     *
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    @Override
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId) {
        return attrGroupMapper.deleteById(attrGroupId);
    }


    /**
     * 导出属性分组列表
     */
    @Override
    public void export(List<AttrGroup> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表" + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), AttrGroup.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 描述:
     * @description:根据 分类id 查询 分组列表以及 分组下的 属性
     * @author: ketd
     * @date: 2024/4/19 11:07
     * @param cateLogId
     * @return: null
     **/
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithoutAttrs(Long cateLogId) {
        try {
            AttrGroup attrGroup = new AttrGroup();
            attrGroup.setCatelogId(cateLogId);
            QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>(attrGroup);
            List<AttrGroup> attrGroups = this.list(queryWrapper);
            List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos = attrGroups.stream().map(item -> {
                        AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
                        BeanUtils.copyProperties(item, attrGroupWithAttrsVo);
                        PageRequest<Long> pageRequest  = new PageRequest<>();
                        pageRequest.setPageNum(0);
                        pageRequest.setPageSize(100);
                        pageRequest.setData(attrGroupWithAttrsVo.getAttrGroupId());
                        List<Attr> attrs = attrService.findAllAttrByAttrGroupId(pageRequest);
                        attrGroupWithAttrsVo.setAttrs(attrs);
                /*List<Attr> attrs = attrService.getRelationAttr(attrGroupWithAttrsVo.getAttrGroupId());
                attrGroupWithAttrsVo.setAttrs(attrs);*/
                        return attrGroupWithAttrsVo;
                    }
            ).collect(Collectors.toList());
            return attrGroupWithAttrsVos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}