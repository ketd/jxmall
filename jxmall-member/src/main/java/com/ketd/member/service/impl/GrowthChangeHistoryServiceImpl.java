package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.GrowthChangeHistoryMapper;
import com.ketd.member.domain.GrowthChangeHistory;
import com.ketd.member.service.IGrowthChangeHistoryService;


/**
 * 成长值变化历史记录Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryMapper, GrowthChangeHistory> implements IGrowthChangeHistoryService {

    @Autowired
    private GrowthChangeHistoryMapper growthChangeHistoryMapper;



    /**
     * 查询成长值变化历史记录
     *
     * @param id 成长值变化历史记录主键
     * @return 成长值变化历史记录
     */
    @Override
    public GrowthChangeHistory selectGrowthChangeHistoryById(Long id)
    {
        return growthChangeHistoryMapper.selectById(id);
    }



    /**
     * 查询成长值变化历史记录列表
     *
     * @param growthChangeHistory 成长值变化历史记录
     * @return 成长值变化历史记录
     */
    @Override
    public List<GrowthChangeHistory> selectGrowthChangeHistoryList(GrowthChangeHistory growthChangeHistory)
    {
        QueryWrapper<GrowthChangeHistory> queryWrapper = new QueryWrapper<>(growthChangeHistory);
        return growthChangeHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 新增成长值变化历史记录
     *
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */

    @Override
    public int insertGrowthChangeHistory(GrowthChangeHistory growthChangeHistory) {
        return growthChangeHistoryMapper.insert(growthChangeHistory);
    }





    /**
     * 修改成长值变化历史记录
     *
     * @param growthChangeHistory 成长值变化历史记录
     * @return 结果
     */

    @Override
    public int updateGrowthChangeHistory(GrowthChangeHistory growthChangeHistory) {
        return growthChangeHistoryMapper.updateById(growthChangeHistory);
    }

    /**
     * 批量删除成长值变化历史记录
     *
     * @param ids 需要删除的成长值变化历史记录主键集合
     * @return 结果
     */
    @Override
    public int deleteGrowthChangeHistoryByIds(Long[] ids) {
        return growthChangeHistoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除成长值变化历史记录信息
     *
     * @param id 成长值变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteGrowthChangeHistoryById(Long id) {
        return growthChangeHistoryMapper.deleteById(id);
    }


    /**
     * 导出成长值变化历史记录列表
     */
    @Override
    public void export(List<GrowthChangeHistory> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            EasyExcel.write(response.getOutputStream(), GrowthChangeHistory.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}