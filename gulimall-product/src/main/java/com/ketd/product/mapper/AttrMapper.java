package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 商品属性Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface AttrMapper extends BaseMapper<Attr>
{
    List<Attr> selectAllByAttrGroupId(@Param("attrGroupId") Long attrGroupId);



}
