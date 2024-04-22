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
import com.ketd.coupon.mapper.SkuLadderMapper;
import com.ketd.coupon.domain.SkuLadder;
import com.ketd.coupon.service.ISkuLadderService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 商品阶梯价格Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderMapper, SkuLadder> implements ISkuLadderService {

    @Autowired
    private SkuLadderMapper skuLadderMapper;



    /**
     * 查询商品阶梯价格
     *
     * @param id 商品阶梯价格主键
     * @return 商品阶梯价格
     */
    @Override
    public SkuLadder selectSkuLadderById(Long id)
    {
        return skuLadderMapper.selectById(id);
    }



    /**
     * 查询商品阶梯价格列表
     *
     * @param skuLadder 商品阶梯价格
     * @return 商品阶梯价格
     */
    @Override
    public List<SkuLadder> selectSkuLadderList(SkuLadder skuLadder)
    {
        QueryWrapper<SkuLadder> queryWrapper = new QueryWrapper<>(skuLadder);
        return skuLadderMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品阶梯价格
     *
     * @param skuLadder 商品阶梯价格
     * @return 结果
     */

    @Override
    public int insertSkuLadder(SkuLadder skuLadder) {
        return skuLadderMapper.insert(skuLadder);
    }





    /**
     * 修改商品阶梯价格
     *
     * @param skuLadder 商品阶梯价格
     * @return 结果
     */

    @Override
    public int updateSkuLadder(SkuLadder skuLadder) {
        return skuLadderMapper.updateById(skuLadder);
    }

    /**
     * 批量删除商品阶梯价格
     *
     * @param ids 需要删除的商品阶梯价格主键集合
     * @return 结果
     */
    @Override
    public int deleteSkuLadderByIds(Long[] ids) {
        return skuLadderMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品阶梯价格信息
     *
     * @param id 商品阶梯价格主键
     * @return 结果
     */
    @Override
    public int deleteSkuLadderById(Long id) {
        return skuLadderMapper.deleteById(id);
    }


    /**
     * 导出商品阶梯价格列表
     */
    @Override
    public void export(List<SkuLadder> list, HttpServletResponse response) {

        extracted(list, response,SkuLadder.class);

    }
}