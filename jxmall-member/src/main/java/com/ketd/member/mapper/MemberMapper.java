package com.ketd.member.mapper;
import org.apache.ibatis.annotations.Param;


import com.ketd.member.domain.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 会员Mapper接口
 * 
 * @author ketd
 * @date 2024-04-18
 */
public interface MemberMapper extends BaseMapper<Member>
{

    Member findOneByMobile(@Param("mobile") String mobile);
    Member findOneByEmail(@Param("email") String email);
    Member findOneByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);
    Member findOneByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
