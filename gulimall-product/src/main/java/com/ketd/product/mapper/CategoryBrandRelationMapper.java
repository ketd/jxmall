package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 品牌分类关联Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface CategoryBrandRelationMapper extends BaseMapper<CategoryBrandRelation>
{


    int updateBrandNameByBrandId(@Param("brandName") String brandName, @Param("brandId") Long brandId);

    int updateCatelogNameByCatelogId(@Param("catelogName") String catelogName, @Param("catelogId") Long catelogId);

    List<CategoryBrandRelation> selectAllByCatelogId(@Param("catelogId") Long catelogId);
}
