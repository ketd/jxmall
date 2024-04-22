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
import com.ketd.coupon.mapper.CouponMapper;
import com.ketd.coupon.domain.Coupon;
import com.ketd.coupon.service.ICouponService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 优惠券信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

    @Autowired
    private CouponMapper couponMapper;



    /**
     * 查询优惠券信息
     *
     * @param id 优惠券信息主键
     * @return 优惠券信息
     */
    @Override
    public Coupon selectCouponById(Long id)
    {
        return couponMapper.selectById(id);
    }



    /**
     * 查询优惠券信息列表
     *
     * @param coupon 优惠券信息
     * @return 优惠券信息
     */
    @Override
    public List<Coupon> selectCouponList(Coupon coupon)
    {
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>(coupon);
        return couponMapper.selectList(queryWrapper);
    }

    /**
     * 新增优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */

    @Override
    public int insertCoupon(Coupon coupon) {
        return couponMapper.insert(coupon);
    }





    /**
     * 修改优惠券信息
     *
     * @param coupon 优惠券信息
     * @return 结果
     */

    @Override
    public int updateCoupon(Coupon coupon) {
        return couponMapper.updateById(coupon);
    }

    /**
     * 批量删除优惠券信息
     *
     * @param ids 需要删除的优惠券信息主键集合
     * @return 结果
     */
    @Override
    public int deleteCouponByIds(Long[] ids) {
        return couponMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除优惠券信息信息
     *
     * @param id 优惠券信息主键
     * @return 结果
     */
    @Override
    public int deleteCouponById(Long id) {
        return couponMapper.deleteById(id);
    }


    /**
     * 导出优惠券信息列表
     */
    @Override
    public void export(List<Coupon> list, HttpServletResponse response) {

        extracted(list, response,Coupon.class);

    }
}