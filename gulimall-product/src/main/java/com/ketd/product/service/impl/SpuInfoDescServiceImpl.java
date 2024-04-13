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
import com.ketd.product.mapper.SpuInfoDescMapper;
import com.ketd.product.domain.SpuInfoDesc;
import com.ketd.product.service.ISpuInfoDescService;


/**
 * spu信息介绍Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDesc> implements ISpuInfoDescService {

    @Autowired
    private SpuInfoDescMapper spuInfoDescMapper;



    /**
     * 查询spu信息介绍
     *
     * @param spuId spu信息介绍主键
     * @return spu信息介绍
     */
    @Override
    public SpuInfoDesc selectSpuInfoDescBySpuId(Long spuId)
    {
        return spuInfoDescMapper.selectById(spuId);
    }



    /**
     * 查询spu信息介绍列表
     *
     * @param spuInfoDesc spu信息介绍
     * @return spu信息介绍
     */
    @Override
    public List<SpuInfoDesc> selectSpuInfoDescList(SpuInfoDesc spuInfoDesc)
    {
        QueryWrapper<SpuInfoDesc> queryWrapper = new QueryWrapper<>(spuInfoDesc);
        return spuInfoDescMapper.selectList(queryWrapper);
    }

    /**
     * 新增spu信息介绍
     *
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */

    @Override
    public int insertSpuInfoDesc(SpuInfoDesc spuInfoDesc) {
        return spuInfoDescMapper.insert(spuInfoDesc);
    }





    /**
     * 修改spu信息介绍
     *
     * @param spuInfoDesc spu信息介绍
     * @return 结果
     */

    @Override
    public int updateSpuInfoDesc(SpuInfoDesc spuInfoDesc) {
        return spuInfoDescMapper.updateById(spuInfoDesc);
    }

    /**
     * 批量删除spu信息介绍
     *
     * @param spuIds 需要删除的spu信息介绍主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuInfoDescBySpuIds(Long[] spuIds) {
        return spuInfoDescMapper.deleteBatchIds(Arrays.asList(spuIds));
    }

    /**
     * 删除spu信息介绍信息
     *
     * @param spuId spu信息介绍主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoDescBySpuId(Long spuId) {
        return spuInfoDescMapper.deleteById(spuId);
    }


    /**
     * 导出spu信息介绍列表
     */
    @Override
    public void export(List<SpuInfoDesc> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SpuInfoDesc.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}