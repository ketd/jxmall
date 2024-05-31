package com.ketd.order.service;


import java.util.List;

import com.ketd.common.result.Result;
import com.ketd.order.vo.SkuCountVo;
import com.ketd.order.vo.SubmitOrderVo;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.order.domain.Order;


/**
 * 订单Service接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface IOrderService  extends IService<Order> {
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public Order selectOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 导出订单列表
     */
    void export(List<Order> list, HttpServletResponse response);

    Result<?> toTrade(List<SkuCountVo> skuCountVo);

    Result<?> submitOrder(SubmitOrderVo submitOrderVo);

    Result<?> getMemberOrders(Integer status);

    Object getMemberOrderInfo(Long id);
}
