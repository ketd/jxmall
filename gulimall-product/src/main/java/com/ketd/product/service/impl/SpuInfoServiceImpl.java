package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.SpuInfoMapper;
import com.ketd.product.domain.SpuInfo;
import com.ketd.product.service.ISpuInfoService;


/**
 * spu信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;



    /**
     * 查询spu信息
     *
     * @param id spu信息主键
     * @return spu信息
     */
    @Override
    public SpuInfo selectSpuInfoById(Long id)
    {
        return spuInfoMapper.selectById(id);
    }



    /**
     * 查询spu信息列表
     *
     * @param spuInfo spu信息
     * @return spu信息
     */
    @Override
    public List<SpuInfo> selectSpuInfoList(SpuInfo spuInfo)
    {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>(spuInfo);
        return spuInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增spu信息
     *
     * @param spuInfo spu信息
     * @return 结果
     */

    @Override
    public int insertSpuInfo(SpuInfo spuInfo) {
        return spuInfoMapper.insert(spuInfo);
    }





    /**
     * 修改spu信息
     *
     * @param spuInfo spu信息
     * @return 结果
     */

    @Override
    public int updateSpuInfo(SpuInfo spuInfo) {
        return spuInfoMapper.updateById(spuInfo);
    }

    /**
     * 批量删除spu信息
     *
     * @param ids 需要删除的spu信息主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuInfoByIds(Long[] ids) {
        return spuInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除spu信息信息
     *
     * @param id spu信息主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoById(Long id) {
        return spuInfoMapper.deleteById(id);
    }


    /**
     * 导出spu信息列表
     */
    @Override
    public void export(List<SpuInfo> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SpuInfo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}