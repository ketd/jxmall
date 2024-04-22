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
import com.ketd.coupon.mapper.CouponSpuCategoryRelationMapper;
import com.ketd.coupon.domain.CouponSpuCategoryRelation;
import com.ketd.coupon.service.ICouponSpuCategoryRelationService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 优惠券分类关联Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationMapper, CouponSpuCategoryRelation> implements ICouponSpuCategoryRelationService {

    @Autowired
    private CouponSpuCategoryRelationMapper couponSpuCategoryRelationMapper;



    /**
     * 查询优惠券分类关联
     *
     * @param id 优惠券分类关联主键
     * @return 优惠券分类关联
     */
    @Override
    public CouponSpuCategoryRelation selectCouponSpuCategoryRelationById(Long id)
    {
        return couponSpuCategoryRelationMapper.selectById(id);
    }



    /**
     * 查询优惠券分类关联列表
     *
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 优惠券分类关联
     */
    @Override
    public List<CouponSpuCategoryRelation> selectCouponSpuCategoryRelationList(CouponSpuCategoryRelation couponSpuCategoryRelation)
    {
        QueryWrapper<CouponSpuCategoryRelation> queryWrapper = new QueryWrapper<>(couponSpuCategoryRelation);
        return couponSpuCategoryRelationMapper.selectList(queryWrapper);
    }

    /**
     * 新增优惠券分类关联
     *
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */

    @Override
    public int insertCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation) {
        return couponSpuCategoryRelationMapper.insert(couponSpuCategoryRelation);
    }





    /**
     * 修改优惠券分类关联
     *
     * @param couponSpuCategoryRelation 优惠券分类关联
     * @return 结果
     */

    @Override
    public int updateCouponSpuCategoryRelation(CouponSpuCategoryRelation couponSpuCategoryRelation) {
        return couponSpuCategoryRelationMapper.updateById(couponSpuCategoryRelation);
    }

    /**
     * 批量删除优惠券分类关联
     *
     * @param ids 需要删除的优惠券分类关联主键集合
     * @return 结果
     */
    @Override
    public int deleteCouponSpuCategoryRelationByIds(Long[] ids) {
        return couponSpuCategoryRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除优惠券分类关联信息
     *
     * @param id 优惠券分类关联主键
     * @return 结果
     */
    @Override
    public int deleteCouponSpuCategoryRelationById(Long id) {
        return couponSpuCategoryRelationMapper.deleteById(id);
    }


    /**
     * 导出优惠券分类关联列表
     */
    @Override
    public void export(List<CouponSpuCategoryRelation> list, HttpServletResponse response) {

        extracted(list, response,CouponSpuCategoryRelation.class);

    }
}