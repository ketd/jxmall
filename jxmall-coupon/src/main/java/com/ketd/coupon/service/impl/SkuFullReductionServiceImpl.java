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
import com.ketd.coupon.mapper.SkuFullReductionMapper;
import com.ketd.coupon.domain.SkuFullReduction;
import com.ketd.coupon.service.ISkuFullReductionService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 商品满减信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction> implements ISkuFullReductionService {

    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;



    /**
     * 查询商品满减信息
     *
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    @Override
    public SkuFullReduction selectSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.selectById(id);
    }



    /**
     * 查询商品满减信息列表
     *
     * @param skuFullReduction 商品满减信息
     * @return 商品满减信息
     */
    @Override
    public List<SkuFullReduction> selectSkuFullReductionList(SkuFullReduction skuFullReduction)
    {
        QueryWrapper<SkuFullReduction> queryWrapper = new QueryWrapper<>(skuFullReduction);
        return skuFullReductionMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */

    @Override
    public int insertSkuFullReduction(SkuFullReduction skuFullReduction) {
        return skuFullReductionMapper.insert(skuFullReduction);
    }





    /**
     * 修改商品满减信息
     *
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */

    @Override
    public int updateSkuFullReduction(SkuFullReduction skuFullReduction) {
        return skuFullReductionMapper.updateById(skuFullReduction);
    }

    /**
     * 批量删除商品满减信息
     *
     * @param ids 需要删除的商品满减信息主键集合
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionByIds(Long[] ids) {
        return skuFullReductionMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品满减信息信息
     *
     * @param id 商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionById(Long id) {
        return skuFullReductionMapper.deleteById(id);
    }


    /**
     * 导出商品满减信息列表
     */
    @Override
    public void export(List<SkuFullReduction> list, HttpServletResponse response) {

        extracted(list, response,SkuFullReduction.class);

    }
}