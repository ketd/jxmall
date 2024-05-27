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
import com.ketd.order.mapper.OrderItemMapper;
import com.ketd.order.domain.OrderItem;
import com.ketd.order.service.IOrderItemService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单项信息Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;



    /**
     * 查询订单项信息
     *
     * @param id 订单项信息主键
     * @return 订单项信息
     */
    @Override
    public OrderItem selectOrderItemById(Long id)
    {
        return orderItemMapper.selectById(id);
    }



    /**
     * 查询订单项信息列表
     *
     * @param orderItem 订单项信息
     * @return 订单项信息
     */
    @Override
    public List<OrderItem> selectOrderItemList(OrderItem orderItem)
    {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>(orderItem);
        return orderItemMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单项信息
     *
     * @param orderItem 订单项信息
     * @return 结果
     */

    @Override
    public int insertOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }





    /**
     * 修改订单项信息
     *
     * @param orderItem 订单项信息
     * @return 结果
     */

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        return orderItemMapper.updateById(orderItem);
    }

    /**
     * 批量删除订单项信息
     *
     * @param ids 需要删除的订单项信息主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderItemByIds(Long[] ids) {
        return orderItemMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单项信息信息
     *
     * @param id 订单项信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemById(Long id) {
        return orderItemMapper.deleteById(id);
    }


    /**
     * 导出订单项信息列表
     */
    @Override
    public void export(List<OrderItem> list, HttpServletResponse response) {

        extracted(list, response,OrderItem.class);

    }
}