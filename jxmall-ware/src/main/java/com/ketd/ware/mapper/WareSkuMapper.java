package com.ketd.ware.mapper;


import com.ketd.common.domain.ware.HasStockTo;
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

    void   addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Long skuNum);


    List<HasStockTo> getSkuHasStock(List<Long> skuIds);
}
