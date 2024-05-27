package com.ketd.order.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.order.mapper.OrderMapper;
import com.ketd.order.domain.Order;
import com.ketd.order.service.IOrderService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;



    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectById(id);
    }



    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>(order);
        return orderMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }





    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids) {
        return orderMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return orderMapper.deleteById(id);
    }


    /**
     * 导出订单列表
     */
    @Override
    public void export(List<Order> list, HttpServletResponse response) {

        extracted(list, response,Order.class);

    }
}