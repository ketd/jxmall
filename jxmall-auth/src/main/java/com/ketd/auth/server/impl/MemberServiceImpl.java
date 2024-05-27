package com.ketd.auth.server.impl;

import com.ketd.auth.Interceptors.LoginProtectedInterceptor;
import com.ketd.auth.server.MemberService;
import com.ketd.auth.vo.MemberInfoVo;
import com.ketd.auth.vo.MemberReceiveAddressVo;
import com.ketd.auth.vo.UpdateMemberInfoVo;
import com.ketd.common.api.member.MemberOpenFeignApi;
import com.ketd.common.api.member.MemberReceiveAddressOpenFeignApi;
import com.ketd.common.domain.member.MemberReceiveAddressTO;
import com.ketd.common.domain.member.MemberTO;
import com.ketd.common.result.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server.impl
 * @Author: ketd
 * @CreateTime: 2024-05-21  17:34
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberOpenFeignApi  memberOpenFeignApi;

    @Autowired
    private MemberReceiveAddressOpenFeignApi memberReceiveAddressOpenFeignApi;

    @Override
    public Result<?> getMemberInfo() {
        MemberTO memberTO = LoginProtectedInterceptor.threadLocal.get();
        MemberInfoVo  memberInfoVo = new MemberInfoVo();
        if (memberTO != null) {
            BeanUtils.copyProperties(memberTO, memberInfoVo);
            return Result.ok(memberInfoVo);
        }else {
            return Result.error(null);
        }
    }

    @Override
    public Result<?> updateMemberInfo(UpdateMemberInfoVo updateMemberInfoVo) {
        MemberTO memberTO = LoginProtectedInterceptor.threadLocal.get();
        try {
            BeanUtils.copyProperties(updateMemberInfoVo, memberTO);
            memberOpenFeignApi.edit(memberTO);
            return Result.ok(null);
        } catch (BeansException e) {
            return Result.error(e);
        }

    }

    @Override
    public Result<?> getMemberAddress() {
        try {
            Long memberId = getCurrentMemberId();
            List<MemberReceiveAddressTO> memberReceiveAddressToList = memberReceiveAddressOpenFeignApi.getMemberAddressById(memberId).getData();
            return Result.ok(memberReceiveAddressToList);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public Result<?> addMemberAddress(MemberReceiveAddressVo memberReceiveAddressVo) {
        try {
            Long memberId = getCurrentMemberId();
            memberReceiveAddressVo.setMemberId(memberId);
            MemberReceiveAddressTO memberReceiveAddressTO = new MemberReceiveAddressTO();
            BeanUtils.copyProperties(memberReceiveAddressVo, memberReceiveAddressTO);
            memberReceiveAddressOpenFeignApi.add(memberReceiveAddressTO);
            return Result.ok(null);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public Result<?> deleteMemberAddress(Long id) {
        try {
            Long[]  ids = {id};
            Long memberId = getCurrentMemberId();
            List<MemberReceiveAddressTO> memberReceiveAddressToList = memberReceiveAddressOpenFeignApi.getMemberAddressById(memberId).getData();
            if (memberReceiveAddressToList.stream().anyMatch(item -> item.getMemberId().equals(memberId))) {
                memberReceiveAddressOpenFeignApi.remove(ids);
            }
            return Result.ok(null);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public Result<?> updateMemberAddress(MemberReceiveAddressVo memberReceiveAddressVo) {
        try {
            Long memberId = getCurrentMemberId();
            List<MemberReceiveAddressTO> memberReceiveAddressToList = memberReceiveAddressOpenFeignApi.getMemberAddressById(memberId).getData();
            if (memberReceiveAddressToList.stream().anyMatch(item -> item.getMemberId().equals(memberId))) {
                MemberReceiveAddressTO memberReceiveAddressTO = new MemberReceiveAddressTO();
                BeanUtils.copyProperties(memberReceiveAddressVo, memberReceiveAddressTO);
                memberReceiveAddressOpenFeignApi.edit(memberReceiveAddressTO);
            }
            return Result.ok(null);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private Long getCurrentMemberId() {
        MemberTO memberTO = LoginProtectedInterceptor.threadLocal.get();
        return memberTO.getId();
    }

    private Result<?> handleException(Exception e) {
        if (e instanceof BeansException) {
            return Result.error(e);
        } else {
            throw new RuntimeException(e);
        }
    }


}
