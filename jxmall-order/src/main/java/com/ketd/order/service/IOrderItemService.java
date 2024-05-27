package com.ketd.order.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.order.domain.OrderItem;


/**
 * 订单项信息Service接口
 * 
 * @author ketd
 * @date 2024-05-27
 */
public interface IOrderItemService  extends IService<OrderItem> {
    /**
     * 查询订单项信息
     * 
     * @param id 订单项信息主键
     * @return 订单项信息
     */
    public OrderItem selectOrderItemById(Long id);

    /**
     * 查询订单项信息列表
     * 
     * @param orderItem 订单项信息
     * @return 订单项信息集合
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 新增订单项信息
     * 
     * @param orderItem 订单项信息
     * @return 结果
     */
    public int insertOrderItem(OrderItem orderItem);

    /**
     * 修改订单项信息
     * 
     * @param orderItem 订单项信息
     * @return 结果
     */
    public int updateOrderItem(OrderItem orderItem);

    /**
     * 批量删除订单项信息
     * 
     * @param ids 需要删除的订单项信息主键集合
     * @return 结果
     */
    public int deleteOrderItemByIds(Long[] ids);

    /**
     * 删除订单项信息信息
     * 
     * @param id 订单项信息主键
     * @return 结果
     */
    public int deleteOrderItemById(Long id);

    /**
     * 导出订单项信息列表
     */
    void export(List<OrderItem> list, HttpServletResponse response);
}
