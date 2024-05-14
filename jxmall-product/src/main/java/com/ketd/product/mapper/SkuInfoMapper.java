package com.ketd.product.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.product.domain.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * sku信息Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface SkuInfoMapper extends BaseMapper<SkuInfo>
{

    List<SkuInfo> findSkuIdBySpuId(@Param("spuId") Long spuId);
}
