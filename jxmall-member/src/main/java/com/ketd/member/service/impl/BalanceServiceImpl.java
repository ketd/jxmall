package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.result.Result;
import com.ketd.member.Interceptors.LoginProtectedInterceptor;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.BalanceMapper;
import com.ketd.member.domain.Balance;
import com.ketd.member.service.IBalanceService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 用户余额表Service业务层处理
 *
 * @author ketd
 * @date 2024-06-04
 */
@Service
@Primary
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IBalanceService {

    @Autowired
    private BalanceMapper balanceMapper;



    /**
     * 查询用户余额表
     *
     * @param id 用户余额表主键
     * @return 用户余额表
     */
    @Override
    public Balance selectBalanceById(Long id)
    {
        return balanceMapper.selectById(id);
    }



    /**
     * 查询用户余额表列表
     *
     * @param balance 用户余额表
     * @return 用户余额表
     */
    @Override
    public List<Balance> selectBalanceList(Balance balance)
    {
        QueryWrapper<Balance> queryWrapper = new QueryWrapper<>(balance);
        return balanceMapper.selectList(queryWrapper);
    }

    /**
     * 新增用户余额表
     *
     * @param balance 用户余额表
     * @return 结果
     */

    @Override
    public int insertBalance(Balance balance) {
        return balanceMapper.insert(balance);
    }





    /**
     * 修改用户余额表
     *
     * @param balance 用户余额表
     * @return 结果
     */

    @Override
    public int updateBalance(Balance balance) {
        return balanceMapper.updateById(balance);
    }

    /**
     * 批量删除用户余额表
     *
     * @param ids 需要删除的用户余额表主键集合
     * @return 结果
     */
    @Override
    public int deleteBalanceByIds(Long[] ids) {
        return balanceMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除用户余额表信息
     *
     * @param id 用户余额表主键
     * @return 结果
     */
    @Override
    public int deleteBalanceById(Long id) {
        return balanceMapper.deleteById(id);
    }


    /**
     * 导出用户余额表列表
     */
    @Override
    public void export(List<Balance> list, HttpServletResponse response) {

        extracted(list, response,Balance.class);

    }

    @Override
    public Result<?> getInfoByMemberId(Long memberId) {
        return Result.ok(balanceMapper.selectOneByMemberId(memberId));
    }

    @Override
    public Result<?> getInfoByMember() {
        MemberTO  memberTO = getCurrentMember();
        return Result.ok(balanceMapper.selectOneByMemberId(memberTO.getId()));
    }


    private MemberTO getCurrentMember() {
        return LoginProtectedInterceptor.threadLocal.get();
    }
}