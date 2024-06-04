package com.ketd.ware.mapper;
import org.apache.ibatis.annotations.Param;


import com.ketd.ware.domain.WareOrderTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 库存工作单Mapper接口
 * 
 * @author ketd
 * @date 2024-04-21
 */
public interface WareOrderTaskMapper extends BaseMapper<WareOrderTask>
{

    WareOrderTask selectOneByOrderSn(@Param("orderSn") String orderSn);
}
