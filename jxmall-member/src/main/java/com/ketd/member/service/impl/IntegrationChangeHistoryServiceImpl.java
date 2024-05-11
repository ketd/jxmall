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
import com.ketd.member.mapper.IntegrationChangeHistoryMapper;
import com.ketd.member.domain.IntegrationChangeHistory;
import com.ketd.member.service.IIntegrationChangeHistoryService;


/**
 * 积分变化历史记录Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryMapper, IntegrationChangeHistory> implements IIntegrationChangeHistoryService {

    @Autowired
    private IntegrationChangeHistoryMapper integrationChangeHistoryMapper;



    /**
     * 查询积分变化历史记录
     *
     * @param id 积分变化历史记录主键
     * @return 积分变化历史记录
     */
    @Override
    public IntegrationChangeHistory selectIntegrationChangeHistoryById(Long id)
    {
        return integrationChangeHistoryMapper.selectById(id);
    }



    /**
     * 查询积分变化历史记录列表
     *
     * @param integrationChangeHistory 积分变化历史记录
     * @return 积分变化历史记录
     */
    @Override
    public List<IntegrationChangeHistory> selectIntegrationChangeHistoryList(IntegrationChangeHistory integrationChangeHistory)
    {
        QueryWrapper<IntegrationChangeHistory> queryWrapper = new QueryWrapper<>(integrationChangeHistory);
        return integrationChangeHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 新增积分变化历史记录
     *
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */

    @Override
    public int insertIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory) {
        return integrationChangeHistoryMapper.insert(integrationChangeHistory);
    }





    /**
     * 修改积分变化历史记录
     *
     * @param integrationChangeHistory 积分变化历史记录
     * @return 结果
     */

    @Override
    public int updateIntegrationChangeHistory(IntegrationChangeHistory integrationChangeHistory) {
        return integrationChangeHistoryMapper.updateById(integrationChangeHistory);
    }

    /**
     * 批量删除积分变化历史记录
     *
     * @param ids 需要删除的积分变化历史记录主键集合
     * @return 结果
     */
    @Override
    public int deleteIntegrationChangeHistoryByIds(Long[] ids) {
        return integrationChangeHistoryMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除积分变化历史记录信息
     *
     * @param id 积分变化历史记录主键
     * @return 结果
     */
    @Override
    public int deleteIntegrationChangeHistoryById(Long id) {
        return integrationChangeHistoryMapper.deleteById(id);
    }


    /**
     * 导出积分变化历史记录列表
     */
    @Override
    public void export(List<IntegrationChangeHistory> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), IntegrationChangeHistory.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}