package com.ketd.ware.mapper;


import com.ketd.ware.domain.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品库存Mapper接口
 * 
 * @author ketd
 * @date 2024-04-21
 */
public interface WareSkuMapper extends BaseMapper<WareSku>
{

    List<Long> listWareIdsHasSku(@Param("skuId") Long skuId);

    void   addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Long skuNum);


    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("count") Integer count);

    WareSku selectOneBySkuIdAndWareId(@Param("skuId") Long skuId, @Param("wareId") Long wareId);

    void unLockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("count") Integer count);
}
