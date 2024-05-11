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
import com.ketd.coupon.mapper.HomeSubjectSpuMapper;
import com.ketd.coupon.domain.HomeSubjectSpu;
import com.ketd.coupon.service.IHomeSubjectSpuService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 专题商品Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpu> implements IHomeSubjectSpuService {

    @Autowired
    private HomeSubjectSpuMapper homeSubjectSpuMapper;



    /**
     * 查询专题商品
     *
     * @param id 专题商品主键
     * @return 专题商品
     */
    @Override
    public HomeSubjectSpu selectHomeSubjectSpuById(Long id)
    {
        return homeSubjectSpuMapper.selectById(id);
    }



    /**
     * 查询专题商品列表
     *
     * @param homeSubjectSpu 专题商品
     * @return 专题商品
     */
    @Override
    public List<HomeSubjectSpu> selectHomeSubjectSpuList(HomeSubjectSpu homeSubjectSpu)
    {
        QueryWrapper<HomeSubjectSpu> queryWrapper = new QueryWrapper<>(homeSubjectSpu);
        return homeSubjectSpuMapper.selectList(queryWrapper);
    }

    /**
     * 新增专题商品
     *
     * @param homeSubjectSpu 专题商品
     * @return 结果
     */

    @Override
    public int insertHomeSubjectSpu(HomeSubjectSpu homeSubjectSpu) {
        return homeSubjectSpuMapper.insert(homeSubjectSpu);
    }





    /**
     * 修改专题商品
     *
     * @param homeSubjectSpu 专题商品
     * @return 结果
     */

    @Override
    public int updateHomeSubjectSpu(HomeSubjectSpu homeSubjectSpu) {
        return homeSubjectSpuMapper.updateById(homeSubjectSpu);
    }

    /**
     * 批量删除专题商品
     *
     * @param ids 需要删除的专题商品主键集合
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectSpuByIds(Long[] ids) {
        return homeSubjectSpuMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除专题商品信息
     *
     * @param id 专题商品主键
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectSpuById(Long id) {
        return homeSubjectSpuMapper.deleteById(id);
    }


    /**
     * 导出专题商品列表
     */
    @Override
    public void export(List<HomeSubjectSpu> list, HttpServletResponse response) {

        extracted(list, response,HomeSubjectSpu.class);

    }
}