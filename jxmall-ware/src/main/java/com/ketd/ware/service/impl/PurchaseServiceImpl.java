package com.ketd.ware.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import com.ketd.ware.domain.PurchaseDetail;
import com.ketd.ware.service.IPurchaseDetailService;
import com.ketd.ware.service.IWareSkuService;
import com.ketd.ware.vo.MergeVo;
import com.ketd.ware.vo.PurchaseDoneVo;
import com.ketd.ware.vo.PurchaseItemDoneVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.ware.mapper.PurchaseMapper;
import com.ketd.ware.domain.Purchase;
import com.ketd.ware.service.IPurchaseService;
import org.springframework.transaction.annotation.Transactional;

import static com.ketd.common.excel.excel.extracted;


/**
 * 采购信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private IPurchaseDetailService purchaseDetailService;

    @Autowired
    private IWareSkuService  wareSkuService;


    /**
     * 查询采购信息
     *
     * @param id 采购信息主键
     * @return 采购信息
     */
    @Override
    public Purchase selectPurchaseById(Long id) {
        return purchaseMapper.selectById(id);
    }


    /**
     * 查询采购信息列表
     *
     * @param purchase 采购信息
     * @return 采购信息
     */
    @Override
    public List<Purchase> selectPurchaseList(Purchase purchase) {
        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>(purchase);
        return purchaseMapper.selectList(queryWrapper);
    }

    /**
     * 新增采购信息
     *
     * @param purchase 采购信息
     * @return 结果
     */

    @Override
    public int insertPurchase(Purchase purchase) {
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());
        return purchaseMapper.insert(purchase);
    }


    /**
     * 修改采购信息
     *
     * @param purchase 采购信息
     * @return 结果
     */

    @Override
    public int updatePurchase(Purchase purchase) {
        purchase.setUpdateTime(new Date());
        return purchaseMapper.updateById(purchase);
    }

    /**
     * 批量删除采购信息
     *
     * @param ids 需要删除的采购信息主键集合
     * @return 结果
     */
    @Override
    public int deletePurchaseByIds(Long[] ids) {
        return purchaseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除采购信息信息
     *
     * @param id 采购信息主键
     * @return 结果
     */
    @Override
    public int deletePurchaseById(Long id) {
        return purchaseMapper.deleteById(id);
    }


    /**
     * 导出采购信息列表
     */
    @Override
    public void export(List<Purchase> list, HttpServletResponse response) {

        extracted(list, response, Purchase.class);

    }

    @Transactional
    @Override
    public Result<?> mergePurchaseDetail(MergeVo mergeVo) {

        try {
            Long purchaseId;

            if (mergeVo.getPurchaseId() == null) {
                Purchase purchase = new Purchase();
                purchase.setStatus(0L);
                purchase.setCreateTime(new Date());
                purchase.setUpdateTime(new Date());
                purchaseMapper.insert(purchase);
                purchaseId = purchase.getId();

            } else {
                purchaseId = mergeVo.getPurchaseId();
            }
            Long[] items = mergeVo.getItems();
            List<PurchaseDetail> purchaseDetails = Arrays.stream(items).map(id -> {
                PurchaseDetail purchaseDetail = new PurchaseDetail();
                purchaseDetail.setPurchaseId(purchaseId);
                purchaseDetail.setStatus(1L);
                purchaseDetail.setId(id);
                return purchaseDetail;
            }).toList();
            purchaseDetailService.updateBatchById(purchaseDetails);
            Purchase purchase = new Purchase();
            purchase.setId(purchaseId);
            purchase.setUpdateTime(new Date());
            purchaseMapper.updateById(purchase);
            return Result.ok(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Transactional
    @Override
    public Result<?> receive(Long[] ids) {

        List<Purchase> purchases = Arrays.stream(ids).map(id -> {
            return purchaseMapper.selectById(id);
        }).filter(purchase -> {
            return purchase.getStatus() == 0L || purchase.getStatus() == 1L;
        }).map(purchase -> {
            purchase.setStatus(2L);
            purchase.setUpdateTime(new Date());
            return purchase;
        }).toList();

        if (purchases.size() != ids.length) {
            return Result.error("没有需要采购的商品");
        }

        this.updateBatchById(purchases);

        purchases.forEach(purchase -> {
          List<PurchaseDetail> purchaseDetails = purchaseDetailService.listDetailByPurchaseId(purchase.getId());
          List<PurchaseDetail> collect = purchaseDetails.stream().map(purchaseDetail -> {
              PurchaseDetail purchaseDetail1 = new PurchaseDetail();
              purchaseDetail1.setId(purchaseDetail.getId());
              purchaseDetail1.setStatus(2L);
              return purchaseDetail1;
          }).toList();
          purchaseDetailService.updateBatchById(collect);

        });


        return Result.ok(null);
    }

    @Override
    public Result<?> done(PurchaseDoneVo doneVo) {
        Long id = doneVo.getId();
        boolean flag = true;
        List<PurchaseDetail> updates= new ArrayList<>();
        List<PurchaseItemDoneVo> items = doneVo.getItems();
        for(PurchaseItemDoneVo  item:items){
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            if(item.getStatus()==4L){
                flag=false;
                purchaseDetail.setStatus(item.getStatus());
            }else{
                purchaseDetail.setStatus(3L);
                PurchaseDetail purchaseDetail1 = purchaseDetailService.getById(item.getItemId());
                wareSkuService.addStock(purchaseDetail1.getSkuId(),purchaseDetail1.getWareId(),purchaseDetail1.getSkuNum());
            }
            purchaseDetail.setId(item.getItemId());
            updates.add(purchaseDetail);
        }
        purchaseDetailService.updateBatchById(updates);

        Purchase  purchase = new Purchase();
        purchase.setId(id);
        purchase.setStatus(flag?3L:4L);
        purchase.setUpdateTime(new Date());
        this.updateById(purchase);


        return Result.ok(null);
    }
}