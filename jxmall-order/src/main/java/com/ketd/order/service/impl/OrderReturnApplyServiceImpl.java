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
import com.ketd.order.mapper.OrderReturnApplyMapper;
import com.ketd.order.domain.OrderReturnApply;
import com.ketd.order.service.IOrderReturnApplyService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 订单退货申请Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApply> implements IOrderReturnApplyService {

    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;



    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请主键
     * @return 订单退货申请
     */
    @Override
    public OrderReturnApply selectOrderReturnApplyById(Long id)
    {
        return orderReturnApplyMapper.selectById(id);
    }



    /**
     * 查询订单退货申请列表
     *
     * @param orderReturnApply 订单退货申请
     * @return 订单退货申请
     */
    @Override
    public List<OrderReturnApply> selectOrderReturnApplyList(OrderReturnApply orderReturnApply)
    {
        QueryWrapper<OrderReturnApply> queryWrapper = new QueryWrapper<>(orderReturnApply);
        return orderReturnApplyMapper.selectList(queryWrapper);
    }

    /**
     * 新增订单退货申请
     *
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */

    @Override
    public int insertOrderReturnApply(OrderReturnApply orderReturnApply) {
        return orderReturnApplyMapper.insert(orderReturnApply);
    }





    /**
     * 修改订单退货申请
     *
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */

    @Override
    public int updateOrderReturnApply(OrderReturnApply orderReturnApply) {
        return orderReturnApplyMapper.updateById(orderReturnApply);
    }

    /**
     * 批量删除订单退货申请
     *
     * @param ids 需要删除的订单退货申请主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderReturnApplyByIds(Long[] ids) {
        return orderReturnApplyMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除订单退货申请信息
     *
     * @param id 订单退货申请主键
     * @return 结果
     */
    @Override
    public int deleteOrderReturnApplyById(Long id) {
        return orderReturnApplyMapper.deleteById(id);
    }


    /**
     * 导出订单退货申请列表
     */
    @Override
    public void export(List<OrderReturnApply> list, HttpServletResponse response) {

        extracted(list, response,OrderReturnApply.class);

    }
}