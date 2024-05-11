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
import com.ketd.coupon.mapper.SeckillSkuRelationMapper;
import com.ketd.coupon.domain.SeckillSkuRelation;
import com.ketd.coupon.service.ISeckillSkuRelationService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 秒杀活动商品关联Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationMapper, SeckillSkuRelation> implements ISeckillSkuRelationService {

    @Autowired
    private SeckillSkuRelationMapper seckillSkuRelationMapper;



    /**
     * 查询秒杀活动商品关联
     *
     * @param id 秒杀活动商品关联主键
     * @return 秒杀活动商品关联
     */
    @Override
    public SeckillSkuRelation selectSeckillSkuRelationById(Long id)
    {
        return seckillSkuRelationMapper.selectById(id);
    }



    /**
     * 查询秒杀活动商品关联列表
     *
     * @param seckillSkuRelation 秒杀活动商品关联
     * @return 秒杀活动商品关联
     */
    @Override
    public List<SeckillSkuRelation> selectSeckillSkuRelationList(SeckillSkuRelation seckillSkuRelation)
    {
        QueryWrapper<SeckillSkuRelation> queryWrapper = new QueryWrapper<>(seckillSkuRelation);
        return seckillSkuRelationMapper.selectList(queryWrapper);
    }

    /**
     * 新增秒杀活动商品关联
     *
     * @param seckillSkuRelation 秒杀活动商品关联
     * @return 结果
     */

    @Override
    public int insertSeckillSkuRelation(SeckillSkuRelation seckillSkuRelation) {
        return seckillSkuRelationMapper.insert(seckillSkuRelation);
    }





    /**
     * 修改秒杀活动商品关联
     *
     * @param seckillSkuRelation 秒杀活动商品关联
     * @return 结果
     */

    @Override
    public int updateSeckillSkuRelation(SeckillSkuRelation seckillSkuRelation) {
        return seckillSkuRelationMapper.updateById(seckillSkuRelation);
    }

    /**
     * 批量删除秒杀活动商品关联
     *
     * @param ids 需要删除的秒杀活动商品关联主键集合
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuRelationByIds(Long[] ids) {
        return seckillSkuRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除秒杀活动商品关联信息
     *
     * @param id 秒杀活动商品关联主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuRelationById(Long id) {
        return seckillSkuRelationMapper.deleteById(id);
    }


    /**
     * 导出秒杀活动商品关联列表
     */
    @Override
    public void export(List<SeckillSkuRelation> list, HttpServletResponse response) {

        extracted(list, response,SeckillSkuRelation.class);

    }
}