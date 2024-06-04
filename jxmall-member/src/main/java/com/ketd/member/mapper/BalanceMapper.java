package com.ketd.member.mapper;
import org.apache.ibatis.annotations.Param;


import com.ketd.member.domain.Balance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 用户余额表Mapper接口
 * 
 * @author ketd
 * @date 2024-06-04
 */
public interface BalanceMapper extends BaseMapper<Balance>
{
    Balance selectOneByMemberId(@Param("memberId") Long memberId);
}
