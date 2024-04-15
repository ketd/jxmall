package com.ketd.product.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.Category;


/**
 * 商品三级分类Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ICategoryService  extends IService<Category> {
    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    public Category selectCategoryByCatId(Long catId);

    /**
     * 查询商品三级分类列表
     * 
     * @param category 商品三级分类
     * @return 商品三级分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的商品三级分类主键集合
     * @return 结果
     */
    public int deleteCategoryByCatIds(Long[] catIds);

    /**
     * 删除商品三级分类信息
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    public int deleteCategoryByCatId(Long catId);

    /**
     * 导出商品三级分类列表
     */
    void export(List<Category> list, HttpServletResponse response);

    List<Category> listWithTree();

    /*
     * 描述:
     * @description: 根据分类id查找分类路径
     * @author: ketd
     * @date: 2024/4/15 19:54
     * @param null
     * @return: null
     **/
    Long[] findCategoryPath(Long attrGroupId);
}
