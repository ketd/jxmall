package com.ketd.member.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.member.domain.MemberCollectSubject;


/**
 * 会员收藏的专题活动Service接口
 * 
 * @author ketd
 * @date 2024-04-18
 */
public interface IMemberCollectSubjectService  extends IService<MemberCollectSubject> {
    /**
     * 查询会员收藏的专题活动
     * 
     * @param id 会员收藏的专题活动主键
     * @return 会员收藏的专题活动
     */
    public MemberCollectSubject selectMemberCollectSubjectById(Long id);

    /**
     * 查询会员收藏的专题活动列表
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 会员收藏的专题活动集合
     */
    public List<MemberCollectSubject> selectMemberCollectSubjectList(MemberCollectSubject memberCollectSubject);

    /**
     * 新增会员收藏的专题活动
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    public int insertMemberCollectSubject(MemberCollectSubject memberCollectSubject);

    /**
     * 修改会员收藏的专题活动
     * 
     * @param memberCollectSubject 会员收藏的专题活动
     * @return 结果
     */
    public int updateMemberCollectSubject(MemberCollectSubject memberCollectSubject);

    /**
     * 批量删除会员收藏的专题活动
     * 
     * @param ids 需要删除的会员收藏的专题活动主键集合
     * @return 结果
     */
    public int deleteMemberCollectSubjectByIds(Long[] ids);

    /**
     * 删除会员收藏的专题活动信息
     * 
     * @param id 会员收藏的专题活动主键
     * @return 结果
     */
    public int deleteMemberCollectSubjectById(Long id);

    /**
     * 导出会员收藏的专题活动列表
     */
    void export(List<MemberCollectSubject> list, HttpServletResponse response);
}
