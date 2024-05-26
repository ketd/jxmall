package com.ketd.member.service;


import java.util.List;

import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.MemberReceiveAddress;


/**
 * 会员收货地址Service接口
 * 
 * @author ketd
 * @date 2024-04-18
 */
public interface IMemberReceiveAddressService  extends IService<MemberReceiveAddress> {
    /**
     * 查询会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
    public MemberReceiveAddress selectMemberReceiveAddressById(Long id);

    /**
     * 查询会员收货地址列表
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    public List<MemberReceiveAddress> selectMemberReceiveAddressList(MemberReceiveAddress memberReceiveAddress);

    /**
     * 新增会员收货地址
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int insertMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress);

    /**
     * 修改会员收货地址
     * 
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int updateMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress);

    /**
     * 批量删除会员收货地址
     * 
     * @param ids 需要删除的会员收货地址主键集合
     * @return 结果
     */
    public int deleteMemberReceiveAddressByIds(Long[] ids);

    /**
     * 删除会员收货地址信息
     * 
     * @param id 会员收货地址主键
     * @return 结果
     */
    public int deleteMemberReceiveAddressById(Long id);

    /**
     * 导出会员收货地址列表
     */
    void export(List<MemberReceiveAddress> list, HttpServletResponse response);

    Result<?> getMemberAddressById(Long id);
}
