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
import com.ketd.coupon.mapper.SeckillPromotionMapper;
import com.ketd.coupon.domain.SeckillPromotion;
import com.ketd.coupon.service.ISeckillPromotionService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 秒杀活动Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionMapper, SeckillPromotion> implements ISeckillPromotionService {

    @Autowired
    private SeckillPromotionMapper seckillPromotionMapper;



    /**
     * 查询秒杀活动
     *
     * @param id 秒杀活动主键
     * @return 秒杀活动
     */
    @Override
    public SeckillPromotion selectSeckillPromotionById(Long id)
    {
        return seckillPromotionMapper.selectById(id);
    }



    /**
     * 查询秒杀活动列表
     *
     * @param seckillPromotion 秒杀活动
     * @return 秒杀活动
     */
    @Override
    public List<SeckillPromotion> selectSeckillPromotionList(SeckillPromotion seckillPromotion)
    {
        QueryWrapper<SeckillPromotion> queryWrapper = new QueryWrapper<>(seckillPromotion);
        return seckillPromotionMapper.selectList(queryWrapper);
    }

    /**
     * 新增秒杀活动
     *
     * @param seckillPromotion 秒杀活动
     * @return 结果
     */

    @Override
    public int insertSeckillPromotion(SeckillPromotion seckillPromotion) {
        return seckillPromotionMapper.insert(seckillPromotion);
    }





    /**
     * 修改秒杀活动
     *
     * @param seckillPromotion 秒杀活动
     * @return 结果
     */

    @Override
    public int updateSeckillPromotion(SeckillPromotion seckillPromotion) {
        return seckillPromotionMapper.updateById(seckillPromotion);
    }

    /**
     * 批量删除秒杀活动
     *
     * @param ids 需要删除的秒杀活动主键集合
     * @return 结果
     */
    @Override
    public int deleteSeckillPromotionByIds(Long[] ids) {
        return seckillPromotionMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除秒杀活动信息
     *
     * @param id 秒杀活动主键
     * @return 结果
     */
    @Override
    public int deleteSeckillPromotionById(Long id) {
        return seckillPromotionMapper.deleteById(id);
    }


    /**
     * 导出秒杀活动列表
     */
    @Override
    public void export(List<SeckillPromotion> list, HttpServletResponse response) {

        extracted(list, response,SeckillPromotion.class);

    }
}