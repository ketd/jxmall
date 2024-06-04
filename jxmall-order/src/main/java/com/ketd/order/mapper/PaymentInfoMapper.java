package com.ketd.order.mapper;
import org.apache.ibatis.annotations.Param;


import com.ketd.order.domain.PaymentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 支付信息Mapper接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface PaymentInfoMapper extends BaseMapper<PaymentInfo>
{

    PaymentInfo selectOneByOrderSn(@Param("orderSn") String orderSn);


}
