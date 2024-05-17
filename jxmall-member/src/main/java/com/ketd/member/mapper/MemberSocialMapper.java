package com.ketd.member.mapper;
import org.apache.ibatis.annotations.Param;


import com.ketd.member.domain.MemberSocial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 单点登录用户信息关联Mapper接口
 * 
 * @author ketd
 * @date 2024-05-17
 */
public interface MemberSocialMapper extends BaseMapper<MemberSocial>
{

    MemberSocial findOneBySocialUidAndType(@Param("socialUid") String socialUid, @Param("type") Integer type);
}
