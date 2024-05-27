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
import com.ketd.order.mapper.OrderOperateHistoryMapper;
import com.ketd.order.domain.OrderOperateHistory;
import com.ketd.order.service.IOrderOperateHistoryService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单操作历史记录Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> implements IOrderOperateHistoryService {

    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;



    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录主键
     * @return 订单操作历史记录
     */
    @Override
    public OrderOperateHistory selectOrderOperateHistoryById(Long id)
    {
        return orderOperateHistoryMapper.selectById(id);
    }



    /**
     * 查询订单操作历史记录列表
     *
     * @param orderOperateHistory 订单操作历史记录
     * @return 订单操作历史记录
     */
    @Override
    public List<OrderOperateHistory> selectOrderOperateHistoryList(OrderOperateHistory orderOperateHistory)
    {
        QueryWrapper<OrderOperateHistory> queryWrapper = new QueryWrapper<>(orderOperateHistory);
        return orderOperateHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单操作历史记录
     *
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */

    @Override
    public int insertOrderOperateHistory(OrderOperateHistory orderOperateHistory) {
        return orderOperateHistoryMapper.insert(orderOperateHistory);
    }





    /**
     * 修改订单操作历史记录
     *
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */

    @Override
    public int updateOrderOperateHistory(OrderOperateHistory orderOperateHistory) {
        return orderOperateHistoryMapper.updateById(orderOperateHistory);
    }

    /**
     * 批量删除订单操作历史记录
     *
     * @param ids 需要删除的订单操作历史记录主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderOperateHistoryByIds(Long[] ids) {
        return orderOperateHistoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录主键
     * @return 结果
     */
    @Override
    public int deleteOrderOperateHistoryById(Long id) {
        return orderOperateHistoryMapper.deleteById(id);
    }


    /**
     * 导出订单操作历史记录列表
     */
    @Override
    public void export(List<OrderOperateHistory> list, HttpServletResponse response) {

        extracted(list, response,OrderOperateHistory.class);

    }
}