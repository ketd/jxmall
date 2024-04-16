package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import com.ketd.product.mapper.CategoryBrandRelationMapper;
import com.ketd.product.mapper.CategoryMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.BrandMapper;
import com.ketd.product.domain.Brand;
import com.ketd.product.service.IBrandService;


/**
 * 品牌Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Autowired
    private CategoryBrandRelationMapper  categoryBrandRelationMapper;



    /**
     * 查询品牌
     *
     * @param brandId 品牌主键
     * @return 品牌
     */
    @Override
    public Brand selectBrandByBrandId(Long brandId)
    {
        return brandMapper.selectById(brandId);
    }



    /**
     * 查询品牌列表
     *
     * @param brand 品牌
     * @return 品牌
     */
    @Override
    public List<Brand> selectBrandList(Brand brand)
    {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>(brand);
        return brandMapper.selectList(queryWrapper);
    }

    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 结果
     */

    @Override
    public int insertBrand(Brand brand) {
        return brandMapper.insert(brand);
    }





    /**
     * 修改品牌
     *
     * @param brand 品牌
     * @return 结果
     */

    @Override
    public Result<?> updateBrand(Brand brand) {

        try {
            categoryBrandRelationMapper.updateBrandNameByBrandId(brand.getName(),brand.getBrandId());
            brandMapper.updateById(brand);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    /**
     * 批量删除品牌
     *
     * @param brandIds 需要删除的品牌主键集合
     * @return 结果
     */
    @Override
    public int deleteBrandByBrandIds(Long[] brandIds) {
        return brandMapper.deleteBatchIds(Arrays.asList(brandIds));
    }

    /**
     * 删除品牌信息
     *
     * @param brandId 品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandByBrandId(Long brandId) {
        return brandMapper.deleteById(brandId);
    }


    /**
     * 导出品牌列表
     */
    @Override
    public void export(List<Brand> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), Brand.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}