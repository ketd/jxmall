package com.ketd.product.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.SkuImages;


/**
 * sku图片Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ISkuImagesService  extends IService<SkuImages> {
    /**
     * 查询sku图片
     * 
     * @param id sku图片主键
     * @return sku图片
     */
    public SkuImages selectSkuImagesById(Long id);

    /**
     * 查询sku图片列表
     * 
     * @param skuImages sku图片
     * @return sku图片集合
     */
    public List<SkuImages> selectSkuImagesList(SkuImages skuImages);

    /**
     * 新增sku图片
     * 
     * @param skuImages sku图片
     * @return 结果
     */
    public int insertSkuImages(SkuImages skuImages);

    /**
     * 修改sku图片
     * 
     * @param skuImages sku图片
     * @return 结果
     */
    public int updateSkuImages(SkuImages skuImages);

    /**
     * 批量删除sku图片
     * 
     * @param ids 需要删除的sku图片主键集合
     * @return 结果
     */
    public int deleteSkuImagesByIds(Long[] ids);

    /**
     * 删除sku图片信息
     * 
     * @param id sku图片主键
     * @return 结果
     */
    public int deleteSkuImagesById(Long id);

    /**
     * 导出sku图片列表
     */
    void export(List<SkuImages> list, HttpServletResponse response);
}
