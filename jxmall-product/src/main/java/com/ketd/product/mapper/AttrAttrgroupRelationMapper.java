package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 属性&属性分组关联Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelation>
{
    AttrAttrgroupRelation findOneByAttrId(@Param("attrId") Long attrId);

    List<AttrAttrgroupRelation> findAllByAttrGroupId(@Param("attrGroupId") Long attrGroupId);

    List<AttrAttrgroupRelation> selectByAttrGroupIds(@Param("attrGroupIds") List<Long> attrGroupIds);

}
