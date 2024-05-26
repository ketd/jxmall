package com.ketd.member.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.member.mapper.MemberReceiveAddressMapper;
import com.ketd.member.domain.MemberReceiveAddress;
import com.ketd.member.service.IMemberReceiveAddressService;


/**
 * 会员收货地址Service业务层处理
 *
 * @author ketd
 * @date 2024-04-18
 */
@Service
@Primary
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddress> implements IMemberReceiveAddressService {

    @Autowired
    private MemberReceiveAddressMapper memberReceiveAddressMapper;



    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
    @Override
    public MemberReceiveAddress selectMemberReceiveAddressById(Long id)
    {
        return memberReceiveAddressMapper.selectById(id);
    }



    /**
     * 查询会员收货地址列表
     *
     * @param memberReceiveAddress 会员收货地址
     * @return 会员收货地址
     */
    @Override
    public List<MemberReceiveAddress> selectMemberReceiveAddressList(MemberReceiveAddress memberReceiveAddress)
    {
        QueryWrapper<MemberReceiveAddress> queryWrapper = new QueryWrapper<>(memberReceiveAddress);
        return memberReceiveAddressMapper.selectList(queryWrapper);
    }

    /**
     * 新增会员收货地址
     *
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */

    @Override
    public int insertMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress) {
        return memberReceiveAddressMapper.insert(memberReceiveAddress);
    }





    /**
     * 修改会员收货地址
     *
     * @param memberReceiveAddress 会员收货地址
     * @return 结果
     */

    @Override
    public int updateMemberReceiveAddress(MemberReceiveAddress memberReceiveAddress) {
        return memberReceiveAddressMapper.updateById(memberReceiveAddress);
    }

    /**
     * 批量删除会员收货地址
     *
     * @param ids 需要删除的会员收货地址主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberReceiveAddressByIds(Long[] ids) {
        return memberReceiveAddressMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员收货地址信息
     *
     * @param id 会员收货地址主键
     * @return 结果
     */
    @Override
    public int deleteMemberReceiveAddressById(Long id) {
        return memberReceiveAddressMapper.deleteById(id);
    }


    /**
     * 导出会员收货地址列表
     */
    @Override
    public void export(List<MemberReceiveAddress> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), MemberReceiveAddress.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result<?> getMemberAddressById(Long id) {
        try {
            List<MemberReceiveAddress> list = memberReceiveAddressMapper.selectList(new QueryWrapper<MemberReceiveAddress>().eq("member_id",id));
            return Result.ok(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}