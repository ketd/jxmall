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
import com.ketd.coupon.mapper.HomeAdvMapper;
import com.ketd.coupon.domain.HomeAdv;
import com.ketd.coupon.service.IHomeAdvService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 首页轮播广告Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvMapper, HomeAdv> implements IHomeAdvService {

    @Autowired
    private HomeAdvMapper homeAdvMapper;



    /**
     * 查询首页轮播广告
     *
     * @param id 首页轮播广告主键
     * @return 首页轮播广告
     */
    @Override
    public HomeAdv selectHomeAdvById(Long id)
    {
        return homeAdvMapper.selectById(id);
    }



    /**
     * 查询首页轮播广告列表
     *
     * @param homeAdv 首页轮播广告
     * @return 首页轮播广告
     */
    @Override
    public List<HomeAdv> selectHomeAdvList(HomeAdv homeAdv)
    {
        QueryWrapper<HomeAdv> queryWrapper = new QueryWrapper<>(homeAdv);
        return homeAdvMapper.selectList(queryWrapper);
    }

    /**
     * 新增首页轮播广告
     *
     * @param homeAdv 首页轮播广告
     * @return 结果
     */

    @Override
    public int insertHomeAdv(HomeAdv homeAdv) {
        return homeAdvMapper.insert(homeAdv);
    }





    /**
     * 修改首页轮播广告
     *
     * @param homeAdv 首页轮播广告
     * @return 结果
     */

    @Override
    public int updateHomeAdv(HomeAdv homeAdv) {
        return homeAdvMapper.updateById(homeAdv);
    }

    /**
     * 批量删除首页轮播广告
     *
     * @param ids 需要删除的首页轮播广告主键集合
     * @return 结果
     */
    @Override
    public int deleteHomeAdvByIds(Long[] ids) {
        return homeAdvMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除首页轮播广告信息
     *
     * @param id 首页轮播广告主键
     * @return 结果
     */
    @Override
    public int deleteHomeAdvById(Long id) {
        return homeAdvMapper.deleteById(id);
    }


    /**
     * 导出首页轮播广告列表
     */
    @Override
    public void export(List<HomeAdv> list, HttpServletResponse response) {

        extracted(list, response,HomeAdv.class);

    }
}