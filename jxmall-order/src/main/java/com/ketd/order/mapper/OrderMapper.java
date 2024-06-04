package com.ketd.order.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.order.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 订单Mapper接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface OrderMapper extends BaseMapper<Order>
{

    List<Order> selectAllByMemberId(@Param("memberId") Long memberId);

    Order selectOneByIdAndMemberId(@Param("id") Long id, @Param("memberId") Long memberId);


    List<Order> selectAllByMemberIdAndStatus(@Param("memberId") Long memberId, @Param("status") Integer status);

    Order selectOneByOrderSn(@Param("orderSn") String orderSn);



    int updateStatusByOrderSn(@Param("status") Integer status, @Param("orderSn") String orderSn);

    int deleteByIdAndMemberId(@Param("id") Long id, @Param("memberId") Long memberId);
}
