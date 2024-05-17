package com.ketd.member.service;


import java.util.List;

import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.MemberSocial;


/**
 * 单点登录用户信息关联Service接口
 * 
 * @author ketd
 * @date 2024-05-17
 */
public interface IMemberSocialService  extends IService<MemberSocial> {
    /**
     * 查询单点登录用户信息关联
     * 
     * @param memberId 单点登录用户信息关联主键
     * @return 单点登录用户信息关联
     */
    public MemberSocial selectMemberSocialByMemberId(Long memberId);

    /**
     * 查询单点登录用户信息关联列表
     * 
     * @param memberSocial 单点登录用户信息关联
     * @return 单点登录用户信息关联集合
     */
    public List<MemberSocial> selectMemberSocialList(MemberSocial memberSocial);

    /**
     * 新增单点登录用户信息关联
     * 
     * @param memberSocial 单点登录用户信息关联
     * @return 结果
     */
    public int insertMemberSocial(MemberSocial memberSocial);

    /**
     * 修改单点登录用户信息关联
     * 
     * @param memberSocial 单点登录用户信息关联
     * @return 结果
     */
    public int updateMemberSocial(MemberSocial memberSocial);

    /**
     * 批量删除单点登录用户信息关联
     * 
     * @param memberIds 需要删除的单点登录用户信息关联主键集合
     * @return 结果
     */
    public int deleteMemberSocialByMemberIds(Long[] memberIds);

    /**
     * 删除单点登录用户信息关联信息
     * 
     * @param memberId 单点登录用户信息关联主键
     * @return 结果
     */
    public int deleteMemberSocialByMemberId(Long memberId);

    /**
     * 导出单点登录用户信息关联列表
     */
    void export(List<MemberSocial> list, HttpServletResponse response);

    Result<?> selectMemberSocialBygetInfoBySocialUid(String socialUid, Integer type);
}
