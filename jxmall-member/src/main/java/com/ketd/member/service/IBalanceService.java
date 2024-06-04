package com.ketd.member.service;


import java.util.List;

import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.Balance;


/**
 * 用户余额表Service接口
 * 
 * @author ketd
 * @date 2024-06-04
 */
public interface IBalanceService  extends IService<Balance> {
    /**
     * 查询用户余额表
     * 
     * @param id 用户余额表主键
     * @return 用户余额表
     */
    public Balance selectBalanceById(Long id);

    /**
     * 查询用户余额表列表
     * 
     * @param balance 用户余额表
     * @return 用户余额表集合
     */
    public List<Balance> selectBalanceList(Balance balance);

    /**
     * 新增用户余额表
     * 
     * @param balance 用户余额表
     * @return 结果
     */
    public int insertBalance(Balance balance);

    /**
     * 修改用户余额表
     * 
     * @param balance 用户余额表
     * @return 结果
     */
    public int updateBalance(Balance balance);

    /**
     * 批量删除用户余额表
     * 
     * @param ids 需要删除的用户余额表主键集合
     * @return 结果
     */
    public int deleteBalanceByIds(Long[] ids);

    /**
     * 删除用户余额表信息
     * 
     * @param id 用户余额表主键
     * @return 结果
     */
    public int deleteBalanceById(Long id);

    /**
     * 导出用户余额表列表
     */
    void export(List<Balance> list, HttpServletResponse response);

    Result<?> getInfoByMemberId(Long memberId);

    Result<?> getInfoByMember();
}
