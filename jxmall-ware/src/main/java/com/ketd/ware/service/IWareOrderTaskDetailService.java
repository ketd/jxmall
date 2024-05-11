package com.ketd.ware.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.ware.domain.WareOrderTaskDetail;


/**
 * 库存工作单Service接口
 * 
 * @author ketd
 * @date 2024-04-21
 */
public interface IWareOrderTaskDetailService  extends IService<WareOrderTaskDetail> {
    /**
     * 查询库存工作单
     * 
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    public WareOrderTaskDetail selectWareOrderTaskDetailById(Long id);

    /**
     * 查询库存工作单列表
     * 
     * @param wareOrderTaskDetail 库存工作单
     * @return 库存工作单集合
     */
    public List<WareOrderTaskDetail> selectWareOrderTaskDetailList(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 新增库存工作单
     * 
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    public int insertWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 修改库存工作单
     * 
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */
    public int updateWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail);

    /**
     * 批量删除库存工作单
     * 
     * @param ids 需要删除的库存工作单主键集合
     * @return 结果
     */
    public int deleteWareOrderTaskDetailByIds(Long[] ids);

    /**
     * 删除库存工作单信息
     * 
     * @param id 库存工作单主键
     * @return 结果
     */
    public int deleteWareOrderTaskDetailById(Long id);

    /**
     * 导出库存工作单列表
     */
    void export(List<WareOrderTaskDetail> list, HttpServletResponse response);
}
