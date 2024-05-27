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
import com.ketd.order.mapper.RefundInfoMapper;
import com.ketd.order.domain.RefundInfo;
import com.ketd.order.service.IRefundInfoService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 退款信息Service业务层处理
 *
 * @author ketd
 * @date 2024-05-27
 */
@Service
@Primary
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo> implements IRefundInfoService {

    @Autowired
    private RefundInfoMapper refundInfoMapper;



    /**
     * 查询退款信息
     *
     * @param id 退款信息主键
     * @return 退款信息
     */
    @Override
    public RefundInfo selectRefundInfoById(Long id)
    {
        return refundInfoMapper.selectById(id);
    }



    /**
     * 查询退款信息列表
     *
     * @param refundInfo 退款信息
     * @return 退款信息
     */
    @Override
    public List<RefundInfo> selectRefundInfoList(RefundInfo refundInfo)
    {
        QueryWrapper<RefundInfo> queryWrapper = new QueryWrapper<>(refundInfo);
        return refundInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增退款信息
     *
     * @param refundInfo 退款信息
     * @return 结果
     */

    @Override
    public int insertRefundInfo(RefundInfo refundInfo) {
        return refundInfoMapper.insert(refundInfo);
    }





    /**
     * 修改退款信息
     *
     * @param refundInfo 退款信息
     * @return 结果
     */

    @Override
    public int updateRefundInfo(RefundInfo refundInfo) {
        return refundInfoMapper.updateById(refundInfo);
    }

    /**
     * 批量删除退款信息
     *
     * @param ids 需要删除的退款信息主键集合
     * @return 结果
     */
    @Override
    public int deleteRefundInfoByIds(Long[] ids) {
        return refundInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除退款信息信息
     *
     * @param id 退款信息主键
     * @return 结果
     */
    @Override
    public int deleteRefundInfoById(Long id) {
        return refundInfoMapper.deleteById(id);
    }


    /**
     * 导出退款信息列表
     */
    @Override
    public void export(List<RefundInfo> list, HttpServletResponse response) {

        extracted(list, response,RefundInfo.class);

    }
}