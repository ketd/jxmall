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
import com.ketd.member.mapper.MemberCollectSubjectMapper;
import com.ketd.member.domain.MemberCollectSubject;
import com.ketd.member.service.IMemberCollectSubjectService;


/**
 * 会员收藏的专题活动Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectMapper, MemberCollectSubject> implements IMemberCollectSubjectService {

    @Autowired
    private MemberCollectSubjectMapper memberCollectSubjectMapper;



    /**
     * 查询会员收藏的专题活动
     *
     * @param id 会员收藏的专题活动主键
     * @return 会员收藏的专题活动
     */
    @Override
    public MemberCollectSubject selectMemberCollectSubjectById(Long id)
    {
        return memberCollectSubjectMapper.selectById(id);
    }



    /**
     * 查询会员收藏的专题活动列表
     *
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 会员收藏的专题活动
     */
    @Override
    public List<MemberCollectSubject> selectMemberCollectSubjectList(MemberCollectSubject memberCollectSubject)
    {
        QueryWrapper<MemberCollectSubject> queryWrapper = new QueryWrapper<>(memberCollectSubject);
        return memberCollectSubjectMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员收藏的专题活动
     *
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */

    @Override
    public int insertMemberCollectSubject(MemberCollectSubject memberCollectSubject) {
        return memberCollectSubjectMapper.insert(memberCollectSubject);
    }





    /**
     * 修改会员收藏的专题活动
     *
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */

    @Override
    public int updateMemberCollectSubject(MemberCollectSubject memberCollectSubject) {
        return memberCollectSubjectMapper.updateById(memberCollectSubject);
    }

    /**
     * 批量删除会员收藏的专题活动
     *
     * @param ids 需要删除的会员收藏的专题活动主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberCollectSubjectByIds(Long[] ids) {
        return memberCollectSubjectMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员收藏的专题活动信息
     *
     * @param id 会员收藏的专题活动主键
     * @return 结果
     */
    @Override
    public int deleteMemberCollectSubjectById(Long id) {
        return memberCollectSubjectMapper.deleteById(id);
    }


    /**
     * 导出会员收藏的专题活动列表
     */
    @Override
    public void export(List<MemberCollectSubject> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberCollectSubject.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}