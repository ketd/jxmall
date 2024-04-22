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
import com.ketd.ware.mapper.WareOrderTaskMapper;
import com.ketd.ware.domain.WareOrderTask;
import com.ketd.ware.service.IWareOrderTaskService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 库存工作单Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper, WareOrderTask> implements IWareOrderTaskService {

    @Autowired
    private WareOrderTaskMapper wareOrderTaskMapper;



    /**
     * 查询库存工作单
     *
     * @param id 库存工作单主键
     * @return 库存工作单
     */
    @Override
    public WareOrderTask selectWareOrderTaskById(Long id)
    {
        return wareOrderTaskMapper.selectById(id);
    }



    /**
     * 查询库存工作单列表
     *
     * @param wareOrderTask 库存工作单
     * @return 库存工作单
     */
    @Override
    public List<WareOrderTask> selectWareOrderTaskList(WareOrderTask wareOrderTask)
    {
        QueryWrapper<WareOrderTask> queryWrapper = new QueryWrapper<>(wareOrderTask);
        return wareOrderTaskMapper.selectList(queryWrapper);
    }

    /**
     * 新增库存工作单
     *
     * @param wareOrderTask 库存工作单
     * @return 结果
     */

    @Override
    public int insertWareOrderTask(WareOrderTask wareOrderTask) {
        return wareOrderTaskMapper.insert(wareOrderTask);
    }





    /**
     * 修改库存工作单
     *
     * @param wareOrderTask 库存工作单
     * @return 结果
     */

    @Override
    public int updateWareOrderTask(WareOrderTask wareOrderTask) {
        return wareOrderTaskMapper.updateById(wareOrderTask);
    }

    /**
     * 批量删除库存工作单
     *
     * @param ids 需要删除的库存工作单主键集合
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskByIds(Long[] ids) {
        return wareOrderTaskMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除库存工作单信息
     *
     * @param id 库存工作单主键
     * @return 结果
     */
    @Override
    public int deleteWareOrderTaskById(Long id) {
        return wareOrderTaskMapper.deleteById(id);
    }


    /**
     * 导出库存工作单列表
     */
    @Override
    public void export(List<WareOrderTask> list, HttpServletResponse response) {

        extracted(list, response,WareOrderTask.class);

    }
}