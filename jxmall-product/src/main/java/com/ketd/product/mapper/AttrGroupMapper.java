package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.AttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 属性分组Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface AttrGroupMapper extends BaseMapper<AttrGroup>
{
    List<AttrGroup> findAllByCatelogId(@Param("catelogId") Long catelogId);
}
