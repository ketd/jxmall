package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import com.ketd.product.domain.Brand;
import com.ketd.product.domain.Category;
import com.ketd.product.mapper.BrandMapper;
import com.ketd.product.mapper.CategoryMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.CategoryBrandRelationMapper;
import com.ketd.product.domain.CategoryBrandRelation;
import com.ketd.product.service.ICategoryBrandRelationService;


/**
 * 品牌分类关联Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements ICategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationMapper categoryBrandRelationMapper;

    @Autowired
    private BrandMapper  brandMapper;

    @Autowired

    private CategoryMapper  categoryMapper;



    /**
     * 查询品牌分类关联
     *
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    @Override
    public CategoryBrandRelation selectCategoryBrandRelationById(Long id)
    {
        return categoryBrandRelationMapper.selectById(id);
    }



    /**
     * 查询品牌分类关联列表
     *
     * @param categoryBrandRelation 品牌分类关联
     * @return 品牌分类关联
     */
    @Override
    public List<CategoryBrandRelation> selectCategoryBrandRelationList(CategoryBrandRelation categoryBrandRelation)
    {
        QueryWrapper<CategoryBrandRelation> queryWrapper = new QueryWrapper<>(categoryBrandRelation);
        return categoryBrandRelationMapper.selectList(queryWrapper);
    }

    /**
     * 新增品牌分类关联
     *
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */

    @Override
    public Result<?> insertCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation) {

        // 查询
        // 判断品牌是否存在
        // 判断分类是否存在
        if(brandMapper.selectById(categoryBrandRelation.getBrandId()) == null
         || categoryMapper.selectById(categoryBrandRelation.getCatelogId()) == null){
            return Result.error("参数错误");
        }
        //判断分类id是否相同
        if(!categoryBrandRelationMapper.selectList(new QueryWrapper<CategoryBrandRelation>().eq("catelog_id", categoryBrandRelation.getBrandId())).isEmpty()){
            return Result.error("品牌已经关联");
        }
        Brand brand =  brandMapper.selectById(categoryBrandRelation.getBrandId());
        categoryBrandRelation.setBrandName(brand.getName());
        Category  category =  categoryMapper.selectById(categoryBrandRelation.getCatelogId());
        categoryBrandRelation.setCatelogName(category.getName());
        categoryBrandRelationMapper.insert(categoryBrandRelation);
        return Result.ok("新增成功");
    }





    /**
     * 修改品牌分类关联
     *
     * @param categoryBrandRelation 品牌分类关联
     * @return 结果
     */

    @Override
    public int updateCategoryBrandRelation(CategoryBrandRelation categoryBrandRelation) {
        return categoryBrandRelationMapper.updateById(categoryBrandRelation);
    }

    /**
     * 批量删除品牌分类关联
     *
     * @param ids 需要删除的品牌分类关联主键集合
     * @return 结果
     */
    @Override
    public int deleteCategoryBrandRelationByIds(Long[] ids) {
        return categoryBrandRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除品牌分类关联信息
     *
     * @param id 品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCategoryBrandRelationById(Long id) {
        return categoryBrandRelationMapper.deleteById(id);
    }


    /**
     * 导出品牌分类关联列表
     */
    @Override
    public void export(List<CategoryBrandRelation> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), CategoryBrandRelation.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}