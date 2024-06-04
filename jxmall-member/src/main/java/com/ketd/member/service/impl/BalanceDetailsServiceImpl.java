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
import com.ketd.member.mapper.BalanceDetailsMapper;
import com.ketd.member.domain.BalanceDetails;
import com.ketd.member.service.IBalanceDetailsService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 用户余额明细Service业务层处理
 *
 * @author ketd
 * @date 2024-06-04
 */
@Service
@Primary
public class BalanceDetailsServiceImpl extends ServiceImpl<BalanceDetailsMapper, BalanceDetails> implements IBalanceDetailsService {

    @Autowired
    private BalanceDetailsMapper balanceDetailsMapper;



    /**
     * 查询用户余额明细
     *
     * @param id 用户余额明细主键
     * @return 用户余额明细
     */
    @Override
    public BalanceDetails selectBalanceDetailsById(Long id)
    {
        return balanceDetailsMapper.selectById(id);
    }



    /**
     * 查询用户余额明细列表
     *
     * @param balanceDetails 用户余额明细
     * @return 用户余额明细
     */
    @Override
    public List<BalanceDetails> selectBalanceDetailsList(BalanceDetails balanceDetails)
    {
        QueryWrapper<BalanceDetails> queryWrapper = new QueryWrapper<>(balanceDetails);
        return balanceDetailsMapper.selectList(queryWrapper);
    }

    /**
     * 新增用户余额明细
     *
     * @param balanceDetails 用户余额明细
     * @return 结果
     */

    @Override
    public int insertBalanceDetails(BalanceDetails balanceDetails) {
        return balanceDetailsMapper.insert(balanceDetails);
    }





    /**
     * 修改用户余额明细
     *
     * @param balanceDetails 用户余额明细
     * @return 结果
     */

    @Override
    public int updateBalanceDetails(BalanceDetails balanceDetails) {
        return balanceDetailsMapper.updateById(balanceDetails);
    }

    /**
     * 批量删除用户余额明细
     *
     * @param ids 需要删除的用户余额明细主键集合
     * @return 结果
     */
    @Override
    public int deleteBalanceDetailsByIds(Long[] ids) {
        return balanceDetailsMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除用户余额明细信息
     *
     * @param id 用户余额明细主键
     * @return 结果
     */
    @Override
    public int deleteBalanceDetailsById(Long id) {
        return balanceDetailsMapper.deleteById(id);
    }


    /**
     * 导出用户余额明细列表
     */
    @Override
    public void export(List<BalanceDetails> list, HttpServletResponse response) {

        extracted(list, response,BalanceDetails.class);

    }
}