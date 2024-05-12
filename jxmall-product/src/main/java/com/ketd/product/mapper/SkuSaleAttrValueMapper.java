package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.SkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * sku销售属性&值Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue>
{
    List<SkuSaleAttrValue> findAllBySkuId(@Param("skuId") Long skuId);

}
