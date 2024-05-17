package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.MemberSocialMapper;
import com.ketd.member.domain.MemberSocial;
import com.ketd.member.service.IMemberSocialService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 单点登录用户信息关联Service业务层处理
 *
 * @author ketd
 * @date 2024-05-17
 */
@Service
@Primary
public class MemberSocialServiceImpl extends ServiceImpl<MemberSocialMapper, MemberSocial> implements IMemberSocialService {

    @Autowired
    private MemberSocialMapper memberSocialMapper;



    /**
     * 查询单点登录用户信息关联
     *
     * @param memberId 单点登录用户信息关联主键
     * @return 单点登录用户信息关联
     */
    @Override
    public MemberSocial selectMemberSocialByMemberId(Long memberId)
    {
        return memberSocialMapper.selectById(memberId);
    }



    /**
     * 查询单点登录用户信息关联列表
     *
     * @param memberSocial 单点登录用户信息关联
     * @return 单点登录用户信息关联
     */
    @Override
    public List<MemberSocial> selectMemberSocialList(MemberSocial memberSocial)
    {
        QueryWrapper<MemberSocial> queryWrapper = new QueryWrapper<>(memberSocial);
        return memberSocialMapper.selectList(queryWrapper);
    }

    /**
     * 新增单点登录用户信息关联
     *
     * @param memberSocial 单点登录用户信息关联
     * @return 结果
     */

    @Override
    public int insertMemberSocial(MemberSocial memberSocial) {
        return memberSocialMapper.insert(memberSocial);
    }





    /**
     * 修改单点登录用户信息关联
     *
     * @param memberSocial 单点登录用户信息关联
     * @return 结果
     */

    @Override
    public int updateMemberSocial(MemberSocial memberSocial) {
        return memberSocialMapper.updateById(memberSocial);
    }

    /**
     * 批量删除单点登录用户信息关联
     *
     * @param memberIds 需要删除的单点登录用户信息关联主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberSocialByMemberIds(Long[] memberIds) {
        return memberSocialMapper.deleteBatchIds(Arrays.asList(memberIds));
    }

    /**
     * 删除单点登录用户信息关联信息
     *
     * @param memberId 单点登录用户信息关联主键
     * @return 结果
     */
    @Override
    public int deleteMemberSocialByMemberId(Long memberId) {
        return memberSocialMapper.deleteById(memberId);
    }


    /**
     * 导出单点登录用户信息关联列表
     */
    @Override
    public void export(List<MemberSocial> list, HttpServletResponse response) {

        extracted(list, response,MemberSocial.class);

    }

    @Override
    public Result<?> selectMemberSocialBygetInfoBySocialUid(String socialUid, Integer type) {


        MemberSocial memberSocial = memberSocialMapper.findOneBySocialUidAndType(socialUid,type);
        if(memberSocial!=null){
            return Result.ok(memberSocial);
        }
        return Result.error(null);
    }
}