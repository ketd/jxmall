package com.ketd.order.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.order.domain.OrderReturnApply;


/**
 * 订单退货申请Service接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface IOrderReturnApplyService  extends IService<OrderReturnApply> {
    /**
     * 查询订单退货申请
     * 
     * @param id 订单退货申请主键
     * @return 订单退货申请
     */
    public OrderReturnApply selectOrderReturnApplyById(Long id);

    /**
     * 查询订单退货申请列表
     * 
     * @param orderReturnApply 订单退货申请
     * @return 订单退货申请集合
     */
    public List<OrderReturnApply> selectOrderReturnApplyList(OrderReturnApply orderReturnApply);

    /**
     * 新增订单退货申请
     * 
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */
    public int insertOrderReturnApply(OrderReturnApply orderReturnApply);

    /**
     * 修改订单退货申请
     * 
     * @param orderReturnApply 订单退货申请
     * @return 结果
     */
    public int updateOrderReturnApply(OrderReturnApply orderReturnApply);

    /**
     * 批量删除订单退货申请
     * 
     * @param ids 需要删除的订单退货申请主键集合
     * @return 结果
     */
    public int deleteOrderReturnApplyByIds(Long[] ids);

    /**
     * 删除订单退货申请信息
     * 
     * @param id 订单退货申请主键
     * @return 结果
     */
    public int deleteOrderReturnApplyById(Long id);

    /**
     * 导出订单退货申请列表
     */
    void export(List<OrderReturnApply> list, HttpServletResponse response);
}
