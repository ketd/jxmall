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
import com.ketd.coupon.mapper.SeckillSkuNoticeMapper;
import com.ketd.coupon.domain.SeckillSkuNotice;
import com.ketd.coupon.service.ISeckillSkuNoticeService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 秒杀商品通知订阅Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeMapper, SeckillSkuNotice> implements ISeckillSkuNoticeService {

    @Autowired
    private SeckillSkuNoticeMapper seckillSkuNoticeMapper;



    /**
     * 查询秒杀商品通知订阅
     *
     * @param id 秒杀商品通知订阅主键
     * @return 秒杀商品通知订阅
     */
    @Override
    public SeckillSkuNotice selectSeckillSkuNoticeById(Long id)
    {
        return seckillSkuNoticeMapper.selectById(id);
    }



    /**
     * 查询秒杀商品通知订阅列表
     *
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 秒杀商品通知订阅
     */
    @Override
    public List<SeckillSkuNotice> selectSeckillSkuNoticeList(SeckillSkuNotice seckillSkuNotice)
    {
        QueryWrapper<SeckillSkuNotice> queryWrapper = new QueryWrapper<>(seckillSkuNotice);
        return seckillSkuNoticeMapper.selectList(queryWrapper);
    }

    /**
     * 新增秒杀商品通知订阅
     *
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */

    @Override
    public int insertSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice) {
        return seckillSkuNoticeMapper.insert(seckillSkuNotice);
    }





    /**
     * 修改秒杀商品通知订阅
     *
     * @param seckillSkuNotice 秒杀商品通知订阅
     * @return 结果
     */

    @Override
    public int updateSeckillSkuNotice(SeckillSkuNotice seckillSkuNotice) {
        return seckillSkuNoticeMapper.updateById(seckillSkuNotice);
    }

    /**
     * 批量删除秒杀商品通知订阅
     *
     * @param ids 需要删除的秒杀商品通知订阅主键集合
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuNoticeByIds(Long[] ids) {
        return seckillSkuNoticeMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除秒杀商品通知订阅信息
     *
     * @param id 秒杀商品通知订阅主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSkuNoticeById(Long id) {
        return seckillSkuNoticeMapper.deleteById(id);
    }


    /**
     * 导出秒杀商品通知订阅列表
     */
    @Override
    public void export(List<SeckillSkuNotice> list, HttpServletResponse response) {

        extracted(list, response,SeckillSkuNotice.class);

    }
}