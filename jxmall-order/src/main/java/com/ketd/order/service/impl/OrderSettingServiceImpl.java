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
import com.ketd.order.mapper.OrderSettingMapper;
import com.ketd.order.domain.OrderSetting;
import com.ketd.order.service.IOrderSettingService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单配置信息Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;



    /**
     * 查询订单配置信息
     *
     * @param id 订单配置信息主键
     * @return 订单配置信息
     */
    @Override
    public OrderSetting selectOrderSettingById(Long id)
    {
        return orderSettingMapper.selectById(id);
    }



    /**
     * 查询订单配置信息列表
     *
     * @param orderSetting 订单配置信息
     * @return 订单配置信息
     */
    @Override
    public List<OrderSetting> selectOrderSettingList(OrderSetting orderSetting)
    {
        QueryWrapper<OrderSetting> queryWrapper = new QueryWrapper<>(orderSetting);
        return orderSettingMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单配置信息
     *
     * @param orderSetting 订单配置信息
     * @return 结果
     */

    @Override
    public int insertOrderSetting(OrderSetting orderSetting) {
        return orderSettingMapper.insert(orderSetting);
    }





    /**
     * 修改订单配置信息
     *
     * @param orderSetting 订单配置信息
     * @return 结果
     */

    @Override
    public int updateOrderSetting(OrderSetting orderSetting) {
        return orderSettingMapper.updateById(orderSetting);
    }

    /**
     * 批量删除订单配置信息
     *
     * @param ids 需要删除的订单配置信息主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderSettingByIds(Long[] ids) {
        return orderSettingMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单配置信息信息
     *
     * @param id 订单配置信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderSettingById(Long id) {
        return orderSettingMapper.deleteById(id);
    }


    /**
     * 导出订单配置信息列表
     */
    @Override
    public void export(List<OrderSetting> list, HttpServletResponse response) {

        extracted(list, response,OrderSetting.class);

    }
}