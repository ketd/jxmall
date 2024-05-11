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
import com.ketd.ware.mapper.WareInfoMapper;
import com.ketd.ware.domain.WareInfo;
import com.ketd.ware.service.IWareInfoService;

import static com.ketd.common.excel.excel.extracted;


/**
 * 仓库信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-21
 */
@Service
@Primary
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo> implements IWareInfoService {

    @Autowired
    private WareInfoMapper wareInfoMapper;



    /**
     * 查询仓库信息
     *
     * @param id 仓库信息主键
     * @return 仓库信息
     */
    @Override
    public WareInfo selectWareInfoById(Long id)
    {
        return wareInfoMapper.selectById(id);
    }



    /**
     * 查询仓库信息列表
     *
     * @param wareInfo 仓库信息
     * @return 仓库信息
     */
    @Override
    public List<WareInfo> selectWareInfoList(WareInfo wareInfo)
    {
        QueryWrapper<WareInfo> queryWrapper = new QueryWrapper<>(wareInfo);
        return wareInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */

    @Override
    public int insertWareInfo(WareInfo wareInfo) {
        return wareInfoMapper.insert(wareInfo);
    }





    /**
     * 修改仓库信息
     *
     * @param wareInfo 仓库信息
     * @return 结果
     */

    @Override
    public int updateWareInfo(WareInfo wareInfo) {
        return wareInfoMapper.updateById(wareInfo);
    }

    /**
     * 批量删除仓库信息
     *
     * @param ids 需要删除的仓库信息主键集合
     * @return 结果
     */
    @Override
    public int deleteWareInfoByIds(Long[] ids) {
        return wareInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除仓库信息信息
     *
     * @param id 仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteWareInfoById(Long id) {
        return wareInfoMapper.deleteById(id);
    }


    /**
     * 导出仓库信息列表
     */
    @Override
    public void export(List<WareInfo> list, HttpServletResponse response) {

        extracted(list, response,WareInfo.class);

    }
}