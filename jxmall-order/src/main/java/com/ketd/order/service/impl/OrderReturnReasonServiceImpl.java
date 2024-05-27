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
import com.ketd.order.mapper.OrderReturnReasonMapper;
import com.ketd.order.domain.OrderReturnReason;
import com.ketd.order.service.IOrderReturnReasonService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 退货原因Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReason> implements IOrderReturnReasonService {

    @Autowired
    private OrderReturnReasonMapper orderReturnReasonMapper;



    /**
     * 查询退货原因
     *
     * @param id 退货原因主键
     * @return 退货原因
     */
    @Override
    public OrderReturnReason selectOrderReturnReasonById(Long id)
    {
        return orderReturnReasonMapper.selectById(id);
    }



    /**
     * 查询退货原因列表
     *
     * @param orderReturnReason 退货原因
     * @return 退货原因
     */
    @Override
    public List<OrderReturnReason> selectOrderReturnReasonList(OrderReturnReason orderReturnReason)
    {
        QueryWrapper<OrderReturnReason> queryWrapper = new QueryWrapper<>(orderReturnReason);
        return orderReturnReasonMapper.selectList(queryWrapper);
    }

    /**
     * 新增退货原因
     *
     * @param orderReturnReason 退货原因
     * @return 结果
     */

    @Override
    public int insertOrderReturnReason(OrderReturnReason orderReturnReason) {
        return orderReturnReasonMapper.insert(orderReturnReason);
    }





    /**
     * 修改退货原因
     *
     * @param orderReturnReason 退货原因
     * @return 结果
     */

    @Override
    public int updateOrderReturnReason(OrderReturnReason orderReturnReason) {
        return orderReturnReasonMapper.updateById(orderReturnReason);
    }

    /**
     * 批量删除退货原因
     *
     * @param ids 需要删除的退货原因主键集合
     * @return 结果
     */
    @Override
    public int deleteOrderReturnReasonByIds(Long[] ids) {
        return orderReturnReasonMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除退货原因信息
     *
     * @param id 退货原因主键
     * @return 结果
     */
    @Override
    public int deleteOrderReturnReasonById(Long id) {
        return orderReturnReasonMapper.deleteById(id);
    }


    /**
     * 导出退货原因列表
     */
    @Override
    public void export(List<OrderReturnReason> list, HttpServletResponse response) {

        extracted(list, response,OrderReturnReason.class);

    }
}