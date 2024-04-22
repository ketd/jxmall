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
import com.ketd.member.mapper.MemberStatisticsInfoMapper;
import com.ketd.member.domain.MemberStatisticsInfo;
import com.ketd.member.service.IMemberStatisticsInfoService;


/**
 * 会员统计信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoMapper, MemberStatisticsInfo> implements IMemberStatisticsInfoService {

    @Autowired
    private MemberStatisticsInfoMapper memberStatisticsInfoMapper;



    /**
     * 查询会员统计信息
     *
     * @param id 会员统计信息主键
     * @return 会员统计信息
     */
    @Override
    public MemberStatisticsInfo selectMemberStatisticsInfoById(Long id)
    {
        return memberStatisticsInfoMapper.selectById(id);
    }



    /**
     * 查询会员统计信息列表
     *
     * @param memberStatisticsInfo 会员统计信息
     * @return 会员统计信息
     */
    @Override
    public List<MemberStatisticsInfo> selectMemberStatisticsInfoList(MemberStatisticsInfo memberStatisticsInfo)
    {
        QueryWrapper<MemberStatisticsInfo> queryWrapper = new QueryWrapper<>(memberStatisticsInfo);
        return memberStatisticsInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员统计信息
     *
     * @param memberStatisticsInfo 会员统计信息
     * @return 结果
     */

    @Override
    public int insertMemberStatisticsInfo(MemberStatisticsInfo memberStatisticsInfo) {
        return memberStatisticsInfoMapper.insert(memberStatisticsInfo);
    }





    /**
     * 修改会员统计信息
     *
     * @param memberStatisticsInfo 会员统计信息
     * @return 结果
     */

    @Override
    public int updateMemberStatisticsInfo(MemberStatisticsInfo memberStatisticsInfo) {
        return memberStatisticsInfoMapper.updateById(memberStatisticsInfo);
    }

    /**
     * 批量删除会员统计信息
     *
     * @param ids 需要删除的会员统计信息主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberStatisticsInfoByIds(Long[] ids) {
        return memberStatisticsInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员统计信息信息
     *
     * @param id 会员统计信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberStatisticsInfoById(Long id) {
        return memberStatisticsInfoMapper.deleteById(id);
    }


    /**
     * 导出会员统计信息列表
     */
    @Override
    public void export(List<MemberStatisticsInfo> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberStatisticsInfo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}