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
import com.ketd.coupon.mapper.SeckillSessionMapper;
import com.ketd.coupon.domain.SeckillSession;
import com.ketd.coupon.service.ISeckillSessionService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 秒杀活动场次Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper, SeckillSession> implements ISeckillSessionService {

    @Autowired
    private SeckillSessionMapper seckillSessionMapper;



    /**
     * 查询秒杀活动场次
     *
     * @param id 秒杀活动场次主键
     * @return 秒杀活动场次
     */
    @Override
    public SeckillSession selectSeckillSessionById(Long id)
    {
        return seckillSessionMapper.selectById(id);
    }



    /**
     * 查询秒杀活动场次列表
     *
     * @param seckillSession 秒杀活动场次
     * @return 秒杀活动场次
     */
    @Override
    public List<SeckillSession> selectSeckillSessionList(SeckillSession seckillSession)
    {
        QueryWrapper<SeckillSession> queryWrapper = new QueryWrapper<>(seckillSession);
        return seckillSessionMapper.selectList(queryWrapper);
    }

    /**
     * 新增秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */

    @Override
    public int insertSeckillSession(SeckillSession seckillSession) {
        return seckillSessionMapper.insert(seckillSession);
    }





    /**
     * 修改秒杀活动场次
     *
     * @param seckillSession 秒杀活动场次
     * @return 结果
     */

    @Override
    public int updateSeckillSession(SeckillSession seckillSession) {
        return seckillSessionMapper.updateById(seckillSession);
    }

    /**
     * 批量删除秒杀活动场次
     *
     * @param ids 需要删除的秒杀活动场次主键集合
     * @return 结果
     */
    @Override
    public int deleteSeckillSessionByIds(Long[] ids) {
        return seckillSessionMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除秒杀活动场次信息
     *
     * @param id 秒杀活动场次主键
     * @return 结果
     */
    @Override
    public int deleteSeckillSessionById(Long id) {
        return seckillSessionMapper.deleteById(id);
    }


    /**
     * 导出秒杀活动场次列表
     */
    @Override
    public void export(List<SeckillSession> list, HttpServletResponse response) {

        extracted(list, response,SeckillSession.class);

    }
}