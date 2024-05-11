package com.ketd.ware.service.impl;


import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.domain.ware.HasStockTo;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.ware.mapper.WareSkuMapper;
import com.ketd.ware.domain.WareSku;
import com.ketd.ware.service.IWareSkuService;
import org.springframework.transaction.annotation.Transactional;

import static com.ketd.common.excel.excel.extracted;


/**
 * 商品库存Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements IWareSkuService {

    @Autowired
    private WareSkuMapper wareSkuMapper;

    @Autowired
    private SkuInfoOpenFeignApi skuInfoOpenFeignApi;

    /**
     * 查询商品库存
     *
     * @param id 商品库存主键
     * @return 商品库存
     */
    @Override
    public WareSku selectWareSkuById(Long id)
    {
        return wareSkuMapper.selectById(id);
    }



    /**
     * 查询商品库存列表
     *
     * @param wareSku 商品库存
     * @return 商品库存
     */
    @Override
    public List<WareSku> selectWareSkuList(WareSku wareSku)
    {
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>(wareSku);
        return wareSkuMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */

    @Override
    public int insertWareSku(WareSku wareSku) {
        return wareSkuMapper.insert(wareSku);
    }





    /**
     * 修改商品库存
     *
     * @param wareSku 商品库存
     * @return 结果
     */

    @Override
    public int updateWareSku(WareSku wareSku) {
        return wareSkuMapper.updateById(wareSku);
    }

    /**
     * 批量删除商品库存
     *
     * @param ids 需要删除的商品库存主键集合
     * @return 结果
     */
    @Override
    public int deleteWareSkuByIds(Long[] ids) {
        return wareSkuMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品库存信息
     *
     * @param id 商品库存主键
     * @return 结果
     */
    @Override
    public int deleteWareSkuById(Long id) {
        return wareSkuMapper.deleteById(id);
    }


    /**
     * 导出商品库存列表
     */
    @Override
    public void export(List<WareSku> list, HttpServletResponse response) {

        extracted(list, response,WareSku.class);

    }

    @Transactional
    @Override
    public void addStock(Long skuId, Long wareId, Long skuNum) {
        WareSku wareSku =new WareSku();
        wareSku.setSkuId(skuId);
        wareSku.setWareId(wareId);
        QueryWrapper<WareSku> wrapper=new QueryWrapper<>(wareSku);
        List<WareSku> wareSkus= wareSkuMapper.selectList(wrapper);

        if(wareSkus.isEmpty()){
            wareSku.setStock(skuNum);
            wareSku.setStockLocked(0L);
            try {
                wareSku.setSkuName(skuInfoOpenFeignApi.getInfo(skuId).getData().getSkuName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            wareSkuMapper.insert(wareSku);

        }else{
            wareSkuMapper.addStock(skuId, wareId, skuNum);
        }

    }

    @Override
    public Result<?> hasStock(List<Long> skuIds) {
      List<HasStockTo> hasStockTos=  skuIds.stream().map(skuId -> {
           HasStockTo hasStockTo=new HasStockTo();
           hasStockTo.setSkuId(skuId);
           hasStockTo.setHasStock(false);
           WareSku  queryWareSku= wareSkuMapper.selectById(skuId);
           QueryWrapper<WareSku> wrapper=new QueryWrapper<>(queryWareSku);
           List<WareSku> wareSkus= wareSkuMapper.selectList(wrapper);
           wareSkus.forEach(wareSku -> {
                if(wareSku.getStock()>0){
                    hasStockTo.setHasStock(true);
                }
           });

           return hasStockTo;

       }).toList();
       return Result.ok(hasStockTos);
    }
}