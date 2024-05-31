package com.ketd.ware.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.api.product.SkuInfoOpenFeignApi;
import com.ketd.common.api.product.SpuInfoOpenFeignApi;
import com.ketd.common.domain.order.LockStickResult;
import com.ketd.common.domain.order.SkuCountVo;
import com.ketd.common.domain.order.WareSkuLockTo;
import com.ketd.common.domain.ware.HasStockTo;
import com.ketd.common.domain.ware.WareSkuTO;
import com.ketd.common.result.Result;
import com.ketd.ware.vo.SkuWareHasStock;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
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
    public WareSku selectWareSkuById(Long id) {
        return wareSkuMapper.selectById(id);
    }


    /**
     * 查询商品库存列表
     *
     * @param wareSku 商品库存
     * @return 商品库存
     */
    @Override
    public List<WareSku> selectWareSkuList(WareSku wareSku) {
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

        extracted(list, response, WareSku.class);

    }

    @Transactional
    @Override
    public void addStock(Long skuId, Long wareId, Long skuNum) {
        WareSku wareSku = new WareSku();
        wareSku.setSkuId(skuId);
        wareSku.setWareId(wareId);
        QueryWrapper<WareSku> wrapper = new QueryWrapper<>(wareSku);
        List<WareSku> wareSkus = wareSkuMapper.selectList(wrapper);

        if (wareSkus.isEmpty()) {
            wareSku.setStock(skuNum);
            wareSku.setStockLocked(0L);
            try {
                wareSku.setSkuName(skuInfoOpenFeignApi.getInfo(skuId).getData().getSkuName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            wareSkuMapper.insert(wareSku);

        } else {
            wareSkuMapper.addStock(skuId, wareId, skuNum);
        }

    }

    @Override
    public Result<?> hasStock(List<Long> skuIds) {
        List<HasStockTo> hasStockTos = skuIds.stream().map(skuId -> {
            HasStockTo hasStockTo = new HasStockTo();
            hasStockTo.setSkuId(skuId);
            hasStockTo.setHasStock(false);
            WareSku queryWareSku = wareSkuMapper.selectById(skuId);
            QueryWrapper<WareSku> wrapper = new QueryWrapper<>(queryWareSku);
            List<WareSku> wareSkus = wareSkuMapper.selectList(wrapper);
            wareSkus.forEach(wareSku -> {
                if (wareSku.getStock() > 0) {
                    hasStockTo.setHasStock(true);
                }
            });

            return hasStockTo;

        }).toList();
        return Result.ok(hasStockTos);
    }

    @Override
    public Result<?> hasStockByCount(Long skuId, Integer count) {
        WareSku queryWareSku = wareSkuMapper.selectById(skuId);
        if (queryWareSku == null) {
            return Result.ok(false);
        } else {
            if (queryWareSku.getStock() >= count) {
                return Result.ok(true);
            } else {
                return Result.ok(false);
            }
        }

    }

    @Override
    public Result<?> addList(List<WareSkuTO> wareSkuTOList) {
        System.out.println(wareSkuTOList);
        try {
            List<WareSku> wareSkuList = new ArrayList<>();
            for (WareSkuTO wareSkuTO : wareSkuTOList) {
                WareSku wareSku = new WareSku();
                BeanUtils.copyProperties(wareSkuTO, wareSku);
                wareSkuList.add(wareSku);
            }

            this.saveBatch(wareSkuList);
            return Result.ok(null);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    @Transactional
    public Result<?> orderLockStock(WareSkuLockTo wareSkuLockTo) {
        List<LockStickResult> lockStickResults = new ArrayList<>();
        List<SkuCountVo> wareSkuLockTos = wareSkuLockTo.getLocks();

        List<SkuWareHasStock> skuWareHasStockList = wareSkuLockTos.stream().map(item -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            skuWareHasStock.setSkuId(skuId);
            skuWareHasStock.setCount(item.getSkuCont());
            List<Long> wareIds = wareSkuMapper.listWareIdsHasSku(skuId);
            skuWareHasStock.setWareIds(wareIds);
            return skuWareHasStock;
        }).toList();

        for (SkuWareHasStock skuWareHasStock : skuWareHasStockList) {
            boolean skuStocked = false;
            Long skuId = skuWareHasStock.getSkuId();
            List<Long> wares = skuWareHasStock.getWareIds();
            if (wares == null || wares.isEmpty()) {
                throw new StockLockException("没有库存");
            }
            for (Long wareId : wares) {
                Long count = wareSkuMapper.lockSkuStock(skuId, wareId, skuWareHasStock.getCount());
                if (count == 1) {
                    skuStocked = true;
                    LockStickResult lockStickResult = new LockStickResult();
                    lockStickResult.setSkuId(skuId);
                    lockStickResult.setWareId(wareId);
                    lockStickResult.setCount(skuWareHasStock.getCount());
                    lockStickResult.setLockStock(skuStocked);
                    lockStickResults.add(lockStickResult);
                    break;
                }
            }
            if (!skuStocked) {
                throw new StockLockException("没有库存");
            }
        }
        return Result.ok(lockStickResults);
    }
    public static class StockLockException extends RuntimeException {
        public StockLockException(String message) {
            super(message);
        }
    }

}