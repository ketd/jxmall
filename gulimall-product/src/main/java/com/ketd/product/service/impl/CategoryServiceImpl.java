package com.ketd.product.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.CategoryMapper;
import com.ketd.product.domain.Category;
import com.ketd.product.service.ICategoryService;


/**
 * 商品三级分类Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;



    /**
     * 查询商品三级分类
     *
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Override
    public Category selectCategoryByCatId(Long catId)
    {
        return categoryMapper.selectById(catId);
    }



    /**
     * 查询商品三级分类列表
     *
     * @param category 商品三级分类
     * @return 商品三级分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>(category);
        return categoryMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品三级分类
     *
     * @param category 商品三级分类
     * @return 结果
     */

    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insert(category);
    }





    /**
     * 修改商品三级分类
     *
     * @param category 商品三级分类
     * @return 结果
     */

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateById(category);
    }

    /**
     * 批量删除商品三级分类
     *
     * @param catIds 需要删除的商品三级分类主键集合
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatIds(Long[] catIds) {
        return categoryMapper.deleteBatchIds(Arrays.asList(catIds));
    }

    /**
     * 删除商品三级分类信息
     *
     * @param catId 商品三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatId(Long catId) {
        return categoryMapper.deleteById(catId);
    }


    /**
     * 导出商品三级分类列表
     */
    @Override
    public void export(List<Category> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), Category.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> listWithTree() {

        List<Category> entities= baseMapper.selectList(null);

        return entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid()==0
        ).peek((menu)-> menu.setChildren(getChildrens(menu,entities))).sorted((menu1, menu2)->{
            return Math.toIntExact((menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()));
        }).collect(Collectors.toList());

    }


    private List<Category> getChildrens(Category root,List<Category> all){

        return all.stream().filter(categoryEntity -> {
            return Objects.equals(categoryEntity.getParentCid(), root.getCatId());
        }).peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity,all))).sorted((menu1, menu2)->{
            return Math.toIntExact((menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort()));
        }).collect(Collectors.toList());
    }

    /*
     * 描述:
     * @description: 查询分类路径
     * @author: ketd
     * @date: 2024/4/15 19:54
     * @param null
     * @return: null
     **/
    @Override
    public Long[] findCategoryPath(Long attrGroupId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(attrGroupId, paths);
        Collections.reverse(parentPath);
        return (Long[]) parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> findParentPath(Long attrGroupId, List<Long> paths) {
        paths.add(attrGroupId);
        Category category = this.getById(attrGroupId);
        if (category.getParentCid() != 0) {
            findParentPath(category.getParentCid(), paths);
        }
        return paths;
    }

}