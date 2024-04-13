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
import com.ketd.product.mapper.SpuImagesMapper;
import com.ketd.product.domain.SpuImages;
import com.ketd.product.service.ISpuImagesService;


/**
 * spu图片Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages> implements ISpuImagesService {

    @Autowired
    private SpuImagesMapper spuImagesMapper;



    /**
     * 查询spu图片
     *
     * @param id spu图片主键
     * @return spu图片
     */
    @Override
    public SpuImages selectSpuImagesById(Long id)
    {
        return spuImagesMapper.selectById(id);
    }



    /**
     * 查询spu图片列表
     *
     * @param spuImages spu图片
     * @return spu图片
     */
    @Override
    public List<SpuImages> selectSpuImagesList(SpuImages spuImages)
    {
        QueryWrapper<SpuImages> queryWrapper = new QueryWrapper<>(spuImages);
        return spuImagesMapper.selectList(queryWrapper);
    }

    /**
     * 新增spu图片
     *
     * @param spuImages spu图片
     * @return 结果
     */

    @Override
    public int insertSpuImages(SpuImages spuImages) {
        return spuImagesMapper.insert(spuImages);
    }





    /**
     * 修改spu图片
     *
     * @param spuImages spu图片
     * @return 结果
     */

    @Override
    public int updateSpuImages(SpuImages spuImages) {
        return spuImagesMapper.updateById(spuImages);
    }

    /**
     * 批量删除spu图片
     *
     * @param ids 需要删除的spu图片主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuImagesByIds(Long[] ids) {
        return spuImagesMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除spu图片信息
     *
     * @param id spu图片主键
     * @return 结果
     */
    @Override
    public int deleteSpuImagesById(Long id) {
        return spuImagesMapper.deleteById(id);
    }


    /**
     * 导出spu图片列表
     */
    @Override
    public void export(List<SpuImages> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SpuImages.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}