package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.ProductAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * spu属性值Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValue>
{

    List<ProductAttrValue> findAllBySpuId(@Param("spuId") Long spuId);
}
