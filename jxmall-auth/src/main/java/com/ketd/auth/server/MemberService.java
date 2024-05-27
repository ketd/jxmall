package com.ketd.auth.server;

import com.ketd.auth.vo.MemberReceiveAddressVo;
import com.ketd.auth.vo.UpdateMemberInfoVo;
import com.ketd.common.result.Result;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.auth.server
 * @Author: ketd
 * @CreateTime: 2024-05-21  17:33
 */
public interface MemberService {
    Result<?> getMemberInfo();

    Result<?> updateMemberInfo(UpdateMemberInfoVo updateMemberInfoVo);

    Result<?> getMemberAddress();

    Result<?> addMemberAddress(MemberReceiveAddressVo memberReceiveAddressVo);

    Result<?> deleteMemberAddress(Long id);

    Result<?> updateMemberAddress(MemberReceiveAddressVo memberReceiveAddressVo);
}
