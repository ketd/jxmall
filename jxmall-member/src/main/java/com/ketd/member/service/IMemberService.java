package com.ketd.member.service;


import java.util.List;

import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.Member;


/**
 * 会员Service接口
 * 
 * @author ketd
 * @date 2024-04-18
 */
public interface IMemberService  extends IService<Member> {
    /**
     * 查询会员
     * 
     * @param id 会员主键
     * @return 会员
     */
    public Result<?> selectMemberById(Long id);

    /**
     * 查询会员列表
     * 
     * @param member 会员
     * @return 会员集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员
     * 
     * @param member 会员
     * @return 结果
     */
    public Result<?> insertMember(Member member);

    /**
     * 修改会员
     * 
     * @param member 会员
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除会员
     * 
     * @param ids 需要删除的会员主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    /**
     * 删除会员信息
     * 
     * @param id 会员主键
     * @return 结果
     */
    public int deleteMemberById(Long id);

    /**
     * 导出会员列表
     */
    void export(List<Member> list, HttpServletResponse response);


}
