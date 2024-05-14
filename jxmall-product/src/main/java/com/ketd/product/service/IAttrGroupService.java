package com.ketd.product.service;


import java.util.List;

import com.ketd.common.result.Result;
import com.ketd.product.vo.AttrGroupWithAttrsVo;
import com.ketd.product.vo.SkuItemVo;
import com.ketd.product.vo.SpuItemBaseAttrVo;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.AttrGroup;


/**
 * 属性分组Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface IAttrGroupService  extends IService<AttrGroup> {
    /**
     * 查询属性分组
     * 
     * @param attrGroupId 属性分组主键
     * @return 属性分组
     */
    public AttrGroup selectAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 查询属性分组列表
     * 
     * @param attrGroup 属性分组
     * @return 属性分组集合
     */
    public List<AttrGroup> selectAttrGroupList(AttrGroup attrGroup);

    /**
     * 新增属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    public int insertAttrGroup(AttrGroup attrGroup);

    /**
     * 修改属性分组
     * 
     * @param attrGroup 属性分组
     * @return 结果
     */
    public int updateAttrGroup(AttrGroup attrGroup);

    /**
     * 批量删除属性分组
     * 
     * @param attrGroupIds 需要删除的属性分组主键集合
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupIds(Long[] attrGroupIds);

    /**
     * 删除属性分组信息
     * 
     * @param attrGroupId 属性分组主键
     * @return 结果
     */
    public int deleteAttrGroupByAttrGroupId(Long attrGroupId);

    /**
     * 导出属性分组列表
     */
    void export(List<AttrGroup> list, HttpServletResponse response);

    List<AttrGroupWithAttrsVo> getAttrGroupWithoutAttrs(Long cateLogId);


    List<SpuItemBaseAttrVo> getAttrGroupWithSpuId(Long spuId, Long catalogId);
}
