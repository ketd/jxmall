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
import com.ketd.product.mapper.SkuImagesMapper;
import com.ketd.product.domain.SkuImages;
import com.ketd.product.service.ISkuImagesService;


/**
 * sku图片Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImages> implements ISkuImagesService {

    @Autowired
    private SkuImagesMapper skuImagesMapper;



    /**
     * 查询sku图片
     *
     * @param id sku图片主键
     * @return sku图片
     */
    @Override
    public SkuImages selectSkuImagesById(Long id)
    {
        return skuImagesMapper.selectById(id);
    }



    /**
     * 查询sku图片列表
     *
     * @param skuImages sku图片
     * @return sku图片
     */
    @Override
    public List<SkuImages> selectSkuImagesList(SkuImages skuImages)
    {
        QueryWrapper<SkuImages> queryWrapper = new QueryWrapper<>(skuImages);
        return skuImagesMapper.selectList(queryWrapper);
    }

    /**
     * 新增sku图片
     *
     * @param skuImages sku图片
     * @return 结果
     */

    @Override
    public int insertSkuImages(SkuImages skuImages) {
        return skuImagesMapper.insert(skuImages);
    }





    /**
     * 修改sku图片
     *
     * @param skuImages sku图片
     * @return 结果
     */

    @Override
    public int updateSkuImages(SkuImages skuImages) {
        return skuImagesMapper.updateById(skuImages);
    }

    /**
     * 批量删除sku图片
     *
     * @param ids 需要删除的sku图片主键集合
     * @return 结果
     */
    @Override
    public int deleteSkuImagesByIds(Long[] ids) {
        return skuImagesMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除sku图片信息
     *
     * @param id sku图片主键
     * @return 结果
     */
    @Override
    public int deleteSkuImagesById(Long id) {
        return skuImagesMapper.deleteById(id);
    }


    /**
     * 导出sku图片列表
     */
    @Override
    public void export(List<SkuImages> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SkuImages.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}