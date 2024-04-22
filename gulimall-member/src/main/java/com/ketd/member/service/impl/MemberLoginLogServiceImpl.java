package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.MemberLoginLogMapper;
import com.ketd.member.domain.MemberLoginLog;
import com.ketd.member.service.IMemberLoginLogService;


/**
 * 会员登录记录Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog> implements IMemberLoginLogService {

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;



    /**
     * 查询会员登录记录
     *
     * @param id 会员登录记录主键
     * @return 会员登录记录
     */
    @Override
    public MemberLoginLog selectMemberLoginLogById(Long id)
    {
        return memberLoginLogMapper.selectById(id);
    }



    /**
     * 查询会员登录记录列表
     *
     * @param memberLoginLog 会员登录记录
     * @return 会员登录记录
     */
    @Override
    public List<MemberLoginLog> selectMemberLoginLogList(MemberLoginLog memberLoginLog)
    {
        QueryWrapper<MemberLoginLog> queryWrapper = new QueryWrapper<>(memberLoginLog);
        return memberLoginLogMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员登录记录
     *
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */

    @Override
    public int insertMemberLoginLog(MemberLoginLog memberLoginLog) {
        return memberLoginLogMapper.insert(memberLoginLog);
    }





    /**
     * 修改会员登录记录
     *
     * @param memberLoginLog 会员登录记录
     * @return 结果
     */

    @Override
    public int updateMemberLoginLog(MemberLoginLog memberLoginLog) {
        return memberLoginLogMapper.updateById(memberLoginLog);
    }

    /**
     * 批量删除会员登录记录
     *
     * @param ids 需要删除的会员登录记录主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberLoginLogByIds(Long[] ids) {
        return memberLoginLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员登录记录信息
     *
     * @param id 会员登录记录主键
     * @return 结果
     */
    @Override
    public int deleteMemberLoginLogById(Long id) {
        return memberLoginLogMapper.deleteById(id);
    }


    /**
     * 导出会员登录记录列表
     */
    @Override
    public void export(List<MemberLoginLog> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberLoginLog.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}