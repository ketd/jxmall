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
import com.ketd.member.mapper.MemberCollectSpuMapper;
import com.ketd.member.domain.MemberCollectSpu;
import com.ketd.member.service.IMemberCollectSpuService;


/**
 * 会员收藏的商品Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuMapper, MemberCollectSpu> implements IMemberCollectSpuService {

    @Autowired
    private MemberCollectSpuMapper memberCollectSpuMapper;



    /**
     * 查询会员收藏的商品
     *
     * @param id 会员收藏的商品主键
     * @return 会员收藏的商品
     */
    @Override
    public MemberCollectSpu selectMemberCollectSpuById(Long id)
    {
        return memberCollectSpuMapper.selectById(id);
    }



    /**
     * 查询会员收藏的商品列表
     *
     * @param memberCollectSpu 会员收藏的商品
     * @return 会员收藏的商品
     */
    @Override
    public List<MemberCollectSpu> selectMemberCollectSpuList(MemberCollectSpu memberCollectSpu)
    {
        QueryWrapper<MemberCollectSpu> queryWrapper = new QueryWrapper<>(memberCollectSpu);
        return memberCollectSpuMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员收藏的商品
     *
     * @param memberCollectSpu 会员收藏的商品
     * @return 结果
     */

    @Override
    public int insertMemberCollectSpu(MemberCollectSpu memberCollectSpu) {
        return memberCollectSpuMapper.insert(memberCollectSpu);
    }





    /**
     * 修改会员收藏的商品
     *
     * @param memberCollectSpu 会员收藏的商品
     * @return 结果
     */

    @Override
    public int updateMemberCollectSpu(MemberCollectSpu memberCollectSpu) {
        return memberCollectSpuMapper.updateById(memberCollectSpu);
    }

    /**
     * 批量删除会员收藏的商品
     *
     * @param ids 需要删除的会员收藏的商品主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberCollectSpuByIds(Long[] ids) {
        return memberCollectSpuMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员收藏的商品信息
     *
     * @param id 会员收藏的商品主键
     * @return 结果
     */
    @Override
    public int deleteMemberCollectSpuById(Long id) {
        return memberCollectSpuMapper.deleteById(id);
    }


    /**
     * 导出会员收藏的商品列表
     */
    @Override
    public void export(List<MemberCollectSpu> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberCollectSpu.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}