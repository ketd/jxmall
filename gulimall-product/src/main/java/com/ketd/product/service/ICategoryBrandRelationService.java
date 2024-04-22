package com.ketd.product.service;


import java.util.List;

import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.CategoryBrandRelation;


/**
 * 品牌分类关联Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ICategoryBrandRelationService  extends IService<CategoryBrandRelation> {
    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    public CategoryBrandRelation selectCategoryBrandRelationById(Long id);

    /**
     * 查询品牌分类关联列表
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 品牌分类关联集合
     */
    public List<CategoryBrandRelation> selectCategoryBrandRelationList(CategoryBrandRelation categoryBrandRelation);

    /**
     * 新增品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public Result<?> insertCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation);

    /**
     * 修改品牌分类关联
     * 
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int updateCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation);

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键集合
     * @return 结果
     */
    public int deleteCategoryBrandRelationByIds(Long[] ids);

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    public int deleteCategoryBrandRelationById(Long id);

    /**
     * 导出品牌分类关联列表
     */
    void export(List<CategoryBrandRelation> list, HttpServletResponse response);

    Result<?> selectBrandsByCatId(Long catId);
}
