package com.ketd.product.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ketd.common.result.Result;
import com.ketd.product.utils.RedisUtil;
import com.ketd.product.mapper.CategoryBrandRelationMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.CategoryMapper;
import com.ketd.product.domain.Category;
import com.ketd.product.service.ICategoryService;

import java.util.List;
import java.util.concurrent.TimeUnit;


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

    @Autowired
    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redisson;


    /**
     * 查询商品三级分类
     *
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Override
    public Category selectCategoryByCatId(Long catId) {
        return categoryMapper.selectById(catId);
    }


    /**
     * 查询商品三级分类列表
     *
     * @param category 商品三级分类
     * @return 商品三级分类
     */
    @Override
    public List<Category> selectCategoryList(Category category) {
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
    public Result<?> updateCategory(Category category) {

        try {
            categoryBrandRelationMapper.updateCatelogNameByCatelogId(category.getName(), category.getCatId());
            categoryMapper.updateById(category);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

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
            String fileName = "导出列表" + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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

        String key = "categories_tree";
        List<Category> categories;

        // 使用TypeReference来避免"未检查的赋值"警告
        categories = redisUtil.getJson(key, new TypeReference<List<Category>>() {
        });
        if (categories != null) {
            return categories;
        }

        RLock lock = redisson.getLock("category_tree_lock");


        lock.lock(30, TimeUnit.SECONDS);
        try {
            categories = redisUtil.getJson(key, new TypeReference<List<Category>>() {
            });
            if (categories != null) {
                return categories;
            }

            categories = getCategoriesFromDatabase();

            redisUtil.setJson(key, categories, TimeUnit.HOURS.toSeconds(1));
        } finally {
            lock.unlock();
        }


        return categories;
    }

    public List<Category> getCategoriesFromDatabase() {
        /*System.out.println("从数据库查询分类数据");*/
        // 查询所有分类
        List<Category> entities = baseMapper.selectList(null);

        // 创建一个Map映射，用于快速查找子节点
        Map<Long, List<Category>> categoryMap = entities.stream()
                .collect(Collectors.groupingBy(Category::getParentCid));

        // 获取一级分类，并设置子节点
        List<Category> tree = categoryMap.getOrDefault(0L, new ArrayList<>())
                .stream()
                .peek(menu -> menu.setChildren(getChildrens(menu, categoryMap)))
                .sorted(Comparator.comparingInt(m -> (int) (long) (m.getSort() != null ? m.getSort() : 0L)))
                .collect(Collectors.toList());

        return tree;
    }

    private List<Category> getChildrens(Category root, Map<Long, List<Category>> categoryMap) {
        // 使用映射快速查找子节点，避免递归时的全列表检索
        List<Category> children = categoryMap.getOrDefault(root.getCatId(), new ArrayList<>())
                .stream()
                .peek(child -> child.setChildren(getChildrens(child, categoryMap)))
                .sorted(Comparator.comparingInt(m -> (int) (long) (m.getSort() != null ? m.getSort() : 0L)))
                .collect(Collectors.toList());

        return children;
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