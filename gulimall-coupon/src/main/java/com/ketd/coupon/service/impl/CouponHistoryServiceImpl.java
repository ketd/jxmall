package com.ketd.coupon.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.coupon.mapper.CouponHistoryMapper;
import com.ketd.coupon.domain.CouponHistory;
import com.ketd.coupon.service.ICouponHistoryService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 优惠券领取历史记录Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory> implements ICouponHistoryService {

    @Autowired
    private CouponHistoryMapper couponHistoryMapper;



    /**
     * 查询优惠券领取历史记录
     *
     * @param id 优惠券领取历史记录主键
     * @return 优惠券领取历史记录
     */
    @Override
    public CouponHistory selectCouponHistoryById(Long id)
    {
        return couponHistoryMapper.selectById(id);
    }



    /**
     * 查询优惠券领取历史记录列表
     *
     * @param couponHistory 优惠券领取历史记录
     * @return 优惠券领取历史记录
     */
    @Override
    public List<CouponHistory> selectCouponHistoryList(CouponHistory couponHistory)
    {
        QueryWrapper<CouponHistory> queryWrapper = new QueryWrapper<>(couponHistory);
        return couponHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 新增优惠券领取历史记录
     *
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */

    @Override
    public int insertCouponHistory(CouponHistory couponHistory) {
        return couponHistoryMapper.insert(couponHistory);
    }





    /**
     * 修改优惠券领取历史记录
     *
     * @param couponHistory 优惠券领取历史记录
     * @return 结果
     */

    @Override
    public int updateCouponHistory(CouponHistory couponHistory) {
        return couponHistoryMapper.updateById(couponHistory);
    }

    /**
     * 批量删除优惠券领取历史记录
     *
     * @param ids 需要删除的优惠券领取历史记录主键集合
     * @return 结果
     */
    @Override
    public int deleteCouponHistoryByIds(Long[] ids) {
        return couponHistoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除优惠券领取历史记录信息
     *
     * @param id 优惠券领取历史记录主键
     * @return 结果
     */
    @Override
    public int deleteCouponHistoryById(Long id) {
        return couponHistoryMapper.deleteById(id);
    }


    /**
     * 导出优惠券领取历史记录列表
     */
    @Override
    public void export(List<CouponHistory> list, HttpServletResponse response) {

        extracted(list, response,CouponHistory.class);

    }
}