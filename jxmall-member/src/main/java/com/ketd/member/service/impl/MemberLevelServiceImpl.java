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
import com.ketd.member.mapper.MemberLevelMapper;
import com.ketd.member.domain.MemberLevel;
import com.ketd.member.service.IMemberLevelService;


/**
 * 会员等级Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements IMemberLevelService {

    @Autowired
    private MemberLevelMapper memberLevelMapper;



    /**
     * 查询会员等级
     *
     * @param id 会员等级主键
     * @return 会员等级
     */
    @Override
    public MemberLevel selectMemberLevelById(Long id)
    {
        return memberLevelMapper.selectById(id);
    }



    /**
     * 查询会员等级列表
     *
     * @param memberLevel 会员等级
     * @return 会员等级
     */
    @Override
    public List<MemberLevel> selectMemberLevelList(MemberLevel memberLevel)
    {
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>(memberLevel);
        return memberLevelMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员等级
     *
     * @param memberLevel 会员等级
     * @return 结果
     */

    @Override
    public int insertMemberLevel(MemberLevel memberLevel) {
        return memberLevelMapper.insert(memberLevel);
    }





    /**
     * 修改会员等级
     *
     * @param memberLevel 会员等级
     * @return 结果
     */

    @Override
    public int updateMemberLevel(MemberLevel memberLevel) {
        return memberLevelMapper.updateById(memberLevel);
    }

    /**
     * 批量删除会员等级
     *
     * @param ids 需要删除的会员等级主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberLevelByIds(Long[] ids) {
        return memberLevelMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员等级信息
     *
     * @param id 会员等级主键
     * @return 结果
     */
    @Override
    public int deleteMemberLevelById(Long id) {
        return memberLevelMapper.deleteById(id);
    }


    /**
     * 导出会员等级列表
     */
    @Override
    public void export(List<MemberLevel> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberLevel.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}