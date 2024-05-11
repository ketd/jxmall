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
import com.ketd.coupon.mapper.CouponSpuRelationMapper;
import com.ketd.coupon.domain.CouponSpuRelation;
import com.ketd.coupon.service.ICouponSpuRelationService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 优惠券与产品关联Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationMapper, CouponSpuRelation> implements ICouponSpuRelationService {

    @Autowired
    private CouponSpuRelationMapper couponSpuRelationMapper;



    /**
     * 查询优惠券与产品关联
     *
     * @param id 优惠券与产品关联主键
     * @return 优惠券与产品关联
     */
    @Override
    public CouponSpuRelation selectCouponSpuRelationById(Long id)
    {
        return couponSpuRelationMapper.selectById(id);
    }



    /**
     * 查询优惠券与产品关联列表
     *
     * @param couponSpuRelation 优惠券与产品关联
     * @return 优惠券与产品关联
     */
    @Override
    public List<CouponSpuRelation> selectCouponSpuRelationList(CouponSpuRelation couponSpuRelation)
    {
        QueryWrapper<CouponSpuRelation> queryWrapper = new QueryWrapper<>(couponSpuRelation);
        return couponSpuRelationMapper.selectList(queryWrapper);
    }

    /**
     * 新增优惠券与产品关联
     *
     * @param couponSpuRelation 优惠券与产品关联
     * @return 结果
     */

    @Override
    public int insertCouponSpuRelation(CouponSpuRelation couponSpuRelation) {
        return couponSpuRelationMapper.insert(couponSpuRelation);
    }





    /**
     * 修改优惠券与产品关联
     *
     * @param couponSpuRelation 优惠券与产品关联
     * @return 结果
     */

    @Override
    public int updateCouponSpuRelation(CouponSpuRelation couponSpuRelation) {
        return couponSpuRelationMapper.updateById(couponSpuRelation);
    }

    /**
     * 批量删除优惠券与产品关联
     *
     * @param ids 需要删除的优惠券与产品关联主键集合
     * @return 结果
     */
    @Override
    public int deleteCouponSpuRelationByIds(Long[] ids) {
        return couponSpuRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除优惠券与产品关联信息
     *
     * @param id 优惠券与产品关联主键
     * @return 结果
     */
    @Override
    public int deleteCouponSpuRelationById(Long id) {
        return couponSpuRelationMapper.deleteById(id);
    }


    /**
     * 导出优惠券与产品关联列表
     */
    @Override
    public void export(List<CouponSpuRelation> list, HttpServletResponse response) {

        extracted(list, response,CouponSpuRelation.class);

    }
}