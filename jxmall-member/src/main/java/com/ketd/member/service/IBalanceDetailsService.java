package com.ketd.member.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.BalanceDetails;


/**
 * 用户余额明细Service接口
 *
 * @author ketd
 * @date 2024-06-04
 */
public interface IBalanceDetailsService  extends IService<BalanceDetails> {
    /**
     * 查询用户余额明细
     *
     * @param id 用户余额明细主键
     * @return 用户余额明细
     */
    public BalanceDetails selectBalanceDetailsById(Long id);

    /**
     * 查询用户余额明细列表
     *
     * @param balanceDetails 用户余额明细
     * @return 用户余额明细集合
     */
    public List<BalanceDetails> selectBalanceDetailsList(BalanceDetails balanceDetails);

    /**
     * 新增用户余额明细
     *
     * @param balanceDetails 用户余额明细
     * @return 结果
     */
    public int insertBalanceDetails(BalanceDetails balanceDetails);

    /**
     * 修改用户余额明细
     *
     * @param balanceDetails 用户余额明细
     * @return 结果
     */
    public int updateBalanceDetails(BalanceDetails balanceDetails);

    /**
     * 批量删除用户余额明细
     *
     * @param ids 需要删除的用户余额明细主键集合
     * @return 结果
     */
    public int deleteBalanceDetailsByIds(Long[] ids);

    /**
     * 删除用户余额明细信息
     *
     * @param id 用户余额明细主键
     * @return 结果
     */
    public int deleteBalanceDetailsById(Long id);

    /**
     * 导出用户余额明细列表
     */
    void export(List<BalanceDetails> list, HttpServletResponse response);
}
