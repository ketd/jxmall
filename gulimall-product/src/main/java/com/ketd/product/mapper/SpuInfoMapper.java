package com.ketd.product.mapper;


import com.ketd.product.domain.SpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * spu信息Mapper接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface SpuInfoMapper extends BaseMapper<SpuInfo>
{

    static void updateSpuStatus(@Param("id") Long id, @Param("i") int i) {

    }
}
