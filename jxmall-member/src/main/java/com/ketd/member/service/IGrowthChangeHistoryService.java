package com.ketd.member.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.GrowthChangeHistory;


/**
 * 成长值变化历史记录Service接口
 * 
 * @author ketd
 * @date 2024-04-18
 */
public interface IGrowthChangeHistoryService  extends IService<GrowthChangeHistory> {
    /**
     * 查询成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 成长值变化历史记录
     */
    public GrowthChangeHistory selectGrowthChangeHistoryById(Long id);

    /**
     * 查询成长值变化历史记录列表
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 成长值变化历史记录集合
     */
    public List<GrowthChangeHistory> selectGrowthChangeHistoryList(GrowthChangeHistory growthChangeHistory);

    /**
     * 新增成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int insertGrowthChangeHistory(GrowthChangeHistory growthChangeHistory);

    /**
     * 修改成长值变化历史记录
     * 
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int updateGrowthChangeHistory(GrowthChangeHistory growthChangeHistory);

    /**
     * 批量删除成长值变化历史记录
     * 
     * @param ids 需要删除的成长值变化历史记录主键集合
     * @return 结果
     */
    public int deleteGrowthChangeHistoryByIds(Long[] ids);

    /**
     * 删除成长值变化历史记录信息
     * 
     * @param id 成长值变化历史记录主键
     * @return 结果
     */
    public int deleteGrowthChangeHistoryById(Long id);

    /**
     * 导出成长值变化历史记录列表
     */
    void export(List<GrowthChangeHistory> list, HttpServletResponse response);
}
