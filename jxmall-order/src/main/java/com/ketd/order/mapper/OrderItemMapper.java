package com.ketd.order.mapper;
import java.util.List;

import com.ketd.order.vo.OrderItemVo;
import org.apache.ibatis.annotations.Param;


import com.ketd.order.domain.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 订单项信息Mapper接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface OrderItemMapper extends BaseMapper<OrderItem>
{

    List<OrderItem> selectAllByOrderId(@Param("orderId") Long orderId);


}
