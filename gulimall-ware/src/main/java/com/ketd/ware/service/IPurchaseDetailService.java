package com.ketd.ware.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.ware.domain.PurchaseDetail;


/**
 * 采购详情Service接口
 * 
 * @author ketd
 * @date 2024-04-21
 */
public interface IPurchaseDetailService  extends IService<PurchaseDetail> {
    /**
     * 查询采购详情
     * 
     * @param id 采购详情主键
     * @return 采购详情
     */
    public PurchaseDetail selectPurchaseDetailById(Long id);

    /**
     * 查询采购详情列表
     * 
     * @param purchaseDetail 采购详情
     * @return 采购详情集合
     */
    public List<PurchaseDetail> selectPurchaseDetailList(PurchaseDetail purchaseDetail);

    /**
     * 新增采购详情
     * 
     * @param purchaseDetail 采购详情
     * @return 结果
     */
    public int insertPurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 修改采购详情
     * 
     * @param purchaseDetail 采购详情
     * @return 结果
     */
    public int updatePurchaseDetail(PurchaseDetail purchaseDetail);

    /**
     * 批量删除采购详情
     * 
     * @param ids 需要删除的采购详情主键集合
     * @return 结果
     */
    public int deletePurchaseDetailByIds(Long[] ids);

    /**
     * 删除采购详情信息
     * 
     * @param id 采购详情主键
     * @return 结果
     */
    public int deletePurchaseDetailById(Long id);

    /**
     * 导出采购详情列表
     */
    void export(List<PurchaseDetail> list, HttpServletResponse response);

    List<PurchaseDetail> listDetailByPurchaseId(Long id);
}
