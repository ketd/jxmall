package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.SkuImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * sku图片Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface SkuImagesMapper extends BaseMapper<SkuImages>
{

    List<SkuImages> getAllBySkuId(@Param("skuId") Long skuId);
}
