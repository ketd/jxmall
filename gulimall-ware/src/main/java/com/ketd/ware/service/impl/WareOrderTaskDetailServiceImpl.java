package com.ketd.ware.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.ware.mapper.WareOrderTaskDetailMapper;
import com.ketd.ware.domain.WareOrderTaskDetail;
import com.ketd.ware.service.IWareOrderTaskDetailService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 库存工作单Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailMapper, WareOrderTaskDetail> implements IWareOrderTaskDetailService {

    @Autowired
    private WareOrderTaskDetailMapper wareOrderTaskDetailMapper;



    /**
     * 查询库存工作单
     *
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    @Override
    public WareOrderTaskDetail selectWareOrderTaskDetailById(Long id)
    {
        return wareOrderTaskDetailMapper.selectById(id);
    }



    /**
     * 查询库存工作单列表
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 库存工作单
     */
    @Override
    public List<WareOrderTaskDetail> selectWareOrderTaskDetailList(WareOrderTaskDetail wareOrderTaskDetail)
    {
        QueryWrapper<WareOrderTaskDetail> queryWrapper = new QueryWrapper<>(wareOrderTaskDetail);
        return wareOrderTaskDetailMapper.selectList(queryWrapper);
    }

    /**
     * 新增库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */

    @Override
    public int insertWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail) {
        return wareOrderTaskDetailMapper.insert(wareOrderTaskDetail);
    }





    /**
     * 修改库存工作单
     *
     * @param wareOrderTaskDetail 库存工作单
     * @return 结果
     */

    @Override
    public int updateWareOrderTaskDetail(WareOrderTaskDetail wareOrderTaskDetail) {
        return wareOrderTaskDetailMapper.updateById(wareOrderTaskDetail);
    }

    /**
     * 批量删除库存工作单
     *
     * @param ids 需要删除的库存工作单主键集合
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskDetailByIds(Long[] ids) {
        return wareOrderTaskDetailMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除库存工作单信息
     *
     * @param id 库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskDetailById(Long id) {
        return wareOrderTaskDetailMapper.deleteById(id);
    }


    /**
     * 导出库存工作单列表
     */
    @Override
    public void export(List<WareOrderTaskDetail> list, HttpServletResponse response) {

        extracted(list, response,WareOrderTaskDetail.class);

    }
}