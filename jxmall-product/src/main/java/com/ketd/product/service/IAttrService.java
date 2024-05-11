package com.ketd.product.service;


import java.util.List;

import com.ketd.common.domain.PageRequest;
import com.ketd.common.result.Result;
import com.ketd.product.vo.AttrVo;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.Attr;


/**
 * 商品属性Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface IAttrService  extends IService<Attr> {
    /**
     * 查询商品属性
     * 
     * @param attrId 商品属性主键
     * @return 商品属性
     */
    public Result<?> selectAttrByAttrId(Long attrId);

    /**
     * 查询商品属性列表
     * 
     * @param attr 商品属性
     * @return 商品属性集合
     */
    public List<Attr> selectAttrList(Attr attr);

    /**
     * 新增商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    public int insertAttr(Attr attr);

    /**
     * 修改商品属性
     * 
     * @param attr 商品属性
     * @return 结果
     */
    public int updateAttr(AttrVo attrVo);

    /**
     * 批量删除商品属性
     * 
     * @param attrIds 需要删除的商品属性主键集合
     * @return 结果
     */
    public int deleteAttrByAttrIds(Long[] attrIds);

    /**
     * 删除商品属性信息
     * 
     * @param attrId 商品属性主键
     * @return 结果
     */
    public int deleteAttrByAttrId(Long attrId);

    /**
     * 导出商品属性列表
     */
    void export(List<Attr> list, HttpServletResponse response);

    Result<?> pageAttrList(PageRequest<Attr> pageRequest);

    Result<?> noLinkAttrList(PageRequest<Attr> pageRequest);

    Result<?> linkAttr(Long attrGroupId, Long[] attrId );

    List<Attr>  findAllAttrByAttrGroupId(PageRequest<Long> pageRequest);

    public List<Attr> getRelationAttr(Long attrgroupId);

    List<AttrVo> pageAttrSaleList(Long catalogId);

    List<Long> selectSearchAttrs(List<Long> attrIds);

    Result<?> getInfoByCatelogId(Long catelogId);

    Result<?> findAttrByCatelogId(Long catelogId);
}
