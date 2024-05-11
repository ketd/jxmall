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
import com.ketd.coupon.mapper.SpuBoundsMapper;
import com.ketd.coupon.domain.SpuBounds;
import com.ketd.coupon.service.ISpuBoundsService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 商品spu积分设置Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsMapper, SpuBounds> implements ISpuBoundsService {

    @Autowired
    private SpuBoundsMapper spuBoundsMapper;



    /**
     * 查询商品spu积分设置
     *
     * @param id 商品spu积分设置主键
     * @return 商品spu积分设置
     */
    @Override
    public SpuBounds selectSpuBoundsById(Long id)
    {
        return spuBoundsMapper.selectById(id);
    }



    /**
     * 查询商品spu积分设置列表
     *
     * @param spuBounds 商品spu积分设置
     * @return 商品spu积分设置
     */
    @Override
    public List<SpuBounds> selectSpuBoundsList(SpuBounds spuBounds)
    {
        QueryWrapper<SpuBounds> queryWrapper = new QueryWrapper<>(spuBounds);
        return spuBoundsMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品spu积分设置
     *
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */

    @Override
    public int insertSpuBounds(SpuBounds spuBounds) {
        return spuBoundsMapper.insert(spuBounds);
    }





    /**
     * 修改商品spu积分设置
     *
     * @param spuBounds 商品spu积分设置
     * @return 结果
     */

    @Override
    public int updateSpuBounds(SpuBounds spuBounds) {
        return spuBoundsMapper.updateById(spuBounds);
    }

    /**
     * 批量删除商品spu积分设置
     *
     * @param ids 需要删除的商品spu积分设置主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuBoundsByIds(Long[] ids) {
        return spuBoundsMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品spu积分设置信息
     *
     * @param id 商品spu积分设置主键
     * @return 结果
     */
    @Override
    public int deleteSpuBoundsById(Long id) {
        return spuBoundsMapper.deleteById(id);
    }


    /**
     * 导出商品spu积分设置列表
     */
    @Override
    public void export(List<SpuBounds> list, HttpServletResponse response) {

        extracted(list, response,SpuBounds.class);

    }
}