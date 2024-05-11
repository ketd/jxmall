package com.ketd.coupon.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.coupon.mapper.MemberPriceMapper;
import com.ketd.coupon.domain.MemberPrice;
import com.ketd.coupon.service.IMemberPriceService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 商品会员价格Service业务层处理
 *
 * @author ketd
 * @date 2024-04-20
 */
@Service
@Primary
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPrice> implements IMemberPriceService {

    @Autowired
    private MemberPriceMapper memberPriceMapper;



    /**
     * 查询商品会员价格
     *
     * @param id 商品会员价格主键
     * @return 商品会员价格
     */
    @Override
    public MemberPrice selectMemberPriceById(Long id)
    {
        return memberPriceMapper.selectById(id);
    }



    /**
     * 查询商品会员价格列表
     *
     * @param memberPrice 商品会员价格
     * @return 商品会员价格
     */
    @Override
    public List<MemberPrice> selectMemberPriceList(MemberPrice memberPrice)
    {
        QueryWrapper<MemberPrice> queryWrapper = new QueryWrapper<>(memberPrice);
        return memberPriceMapper.selectList(queryWrapper);
    }

    /**
     * 新增商品会员价格
     *
     * @param memberPrice 商品会员价格
     * @return 结果
     */

    @Override
    public int insertMemberPrice(MemberPrice memberPrice) {
        return memberPriceMapper.insert(memberPrice);
    }





    /**
     * 修改商品会员价格
     *
     * @param memberPrice 商品会员价格
     * @return 结果
     */

    @Override
    public int updateMemberPrice(MemberPrice memberPrice) {
        return memberPriceMapper.updateById(memberPrice);
    }

    /**
     * 批量删除商品会员价格
     *
     * @param ids 需要删除的商品会员价格主键集合
     * @return 结果
     */
    @Override
    public int deleteMemberPriceByIds(Long[] ids) {
        return memberPriceMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除商品会员价格信息
     *
     * @param id 商品会员价格主键
     * @return 结果
     */
    @Override
    public int deleteMemberPriceById(Long id) {
        return memberPriceMapper.deleteById(id);
    }


    /**
     * 导出商品会员价格列表
     */
    @Override
    public void export(List<MemberPrice> list, HttpServletResponse response) {

        extracted(list, response,MemberPrice.class);

    }
}