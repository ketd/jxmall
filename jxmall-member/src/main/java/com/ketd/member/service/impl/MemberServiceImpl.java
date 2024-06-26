package com.ketd.member.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ketd.common.result.Result;
import com.ketd.member.domain.Balance;
import com.ketd.member.domain.Member;
import com.ketd.member.mapper.BalanceMapper;
import com.ketd.member.mapper.MemberMapper;
import com.ketd.member.service.IMemberService;
import com.ketd.member.util.RedisUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 会员Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BalanceMapper  balanceMapper;



    /**
     * 查询会员
     *
     * @param id 会员主键
     * @return 会员
     */
    @Override
    public Result<?> selectMemberById(Long id)
    {
        try {
         Member member = memberMapper.selectById(id);
          return Result.ok(member);
        }catch (Exception e) {
            return Result.error(null);
        }
    }



    /**
     * 查询会员列表
     *
     * @param member 会员
     * @return 会员
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>(member);
        return memberMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员
     *
     * @param member 会员
     * @return 结果
     */

    @Override
    public Result<?> insertMember(Member member) {
        memberMapper.insert(member);

         Balance balance=new Balance();
         balance.setMemberId(member.getId());
         balance.setBalance(BigDecimal.valueOf(1000000.00));
         balance.setStatus(1);
         balance.setCreatedAt(new Date());
         balance.setUpdatedAt(new Date());
         balanceMapper.insert(balance);

        return Result.ok(member);
    }





    /**
     * 修改会员
     *
     * @param member 会员
     * @return 结果
     */

    @Override
    public int updateMember(Member member) {
        return memberMapper.updateById(member);
    }

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(Long[] ids) {
        return memberMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员信息
     *
     * @param id 会员主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteById(id);
    }


    /**
     * 导出会员列表
     */
    @Override
    public void export(List<Member> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            EasyExcel.write(response.getOutputStream(), Member.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}