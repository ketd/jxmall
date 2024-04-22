package com.ketd.ware.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.ware.mapper.PurchaseDetailMapper;
import com.ketd.ware.domain.PurchaseDetail;
import com.ketd.ware.service.IPurchaseDetailService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 采购详情Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements IPurchaseDetailService {

    @Autowired
    private PurchaseDetailMapper purchaseDetailMapper;



    /**
     * 查询采购详情
     *
     * @param id 采购详情主键
     * @return 采购详情
     */
    @Override
    public PurchaseDetail selectPurchaseDetailById(Long id)
    {
        return purchaseDetailMapper.selectById(id);
    }



    /**
     * 查询采购详情列表
     *
     * @param purchaseDetail 采购详情
     * @return 采购详情
     */
    @Override
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail)
    {
        QueryWrapper<PurchaseDetail> queryWrapper = new QueryWrapper<>(purchaseDetail);
        return purchaseDetailMapper.selectList(queryWrapper);
    }

    /**
     * 新增采购详情
     *
     * @param purchaseDetail 采购详情
     * @return 结果
     */

    @Override
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailMapper.insert(purchaseDetail);
    }





    /**
     * 修改采购详情
     *
     * @param purchaseDetail 采购详情
     * @return 结果
     */

    @Override
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailMapper.updateById(purchaseDetail);
    }

    /**
     * 批量删除采购详情
     *
     * @param ids 需要删除的采购详情主键集合
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailByIds(Long[] ids) {
        return purchaseDetailMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除采购详情信息
     *
     * @param id 采购详情主键
     * @return 结果
     */
    @Override
    public int deletePurchaseDetailById(Long id) {
        return purchaseDetailMapper.deleteById(id);
    }


    /**
     * 导出采购详情列表
     */
    @Override
    public void export(List<PurchaseDetail> list, HttpServletResponse response) {

        extracted(list, response,PurchaseDetail.class);

    }

    @Override
    public List<PurchaseDetail> listDetailByPurchaseId(Long id) {
        try {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setPurchaseId(id);
            return purchaseDetailMapper.selectList(new QueryWrapper<>(purchaseDetail));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}