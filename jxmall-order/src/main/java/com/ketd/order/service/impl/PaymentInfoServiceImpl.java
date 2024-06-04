package com.ketd.order.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.order.mapper.PaymentInfoMapper;
import com.ketd.order.domain.PaymentInfo;
import com.ketd.order.service.IPaymentInfoService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 支付信息Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;



    /**
     * 查询支付信息
     *
     * @param id 支付信息主键
     * @return 支付信息
     */
    @Override
    public PaymentInfo selectPaymentInfoById(Long id)
    {
        return paymentInfoMapper.selectById(id);
    }



    /**
     * 查询支付信息列表
     *
     * @param paymentInfo 支付信息
     * @return 支付信息
     */
    @Override
    public List<PaymentInfo> selectPaymentInfoList(PaymentInfo paymentInfo)
    {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>(paymentInfo);
        return paymentInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增支付信息
     *
     * @param paymentInfo 支付信息
     * @return 结果
     */

    @Override
    public int insertPaymentInfo(PaymentInfo paymentInfo) {
        return paymentInfoMapper.insert(paymentInfo);
    }





    /**
     * 修改支付信息
     *
     * @param paymentInfo 支付信息
     * @return 结果
     */

    @Override
    public int updatePaymentInfo(PaymentInfo paymentInfo) {
        return paymentInfoMapper.updateById(paymentInfo);
    }

    /**
     * 批量删除支付信息
     *
     * @param ids 需要删除的支付信息主键集合
     * @return 结果
     */
    @Override
    public int deletePaymentInfoByIds(Long[] ids) {
        return paymentInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除支付信息信息
     *
     * @param id 支付信息主键
     * @return 结果
     */
    @Override
    public int deletePaymentInfoById(Long id) {
        return paymentInfoMapper.deleteById(id);
    }


    /**
     * 导出支付信息列表
     */
    @Override
    public void export(List<PaymentInfo> list, HttpServletResponse response) {

        extracted(list, response,PaymentInfo.class);

    }

    @Override
    public Result<?> getInfoBySn(String orderSn) {
        return Result.ok(paymentInfoMapper.selectOneByOrderSn(orderSn));
    }
}