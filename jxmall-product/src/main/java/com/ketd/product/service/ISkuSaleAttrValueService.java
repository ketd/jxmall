package com.ketd.product.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.SkuSaleAttrValue;


/**
 * sku销售属性&值Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface ISkuSaleAttrValueService  extends IService<SkuSaleAttrValue> {
    /**
     * 查询sku销售属性&值
     * 
     * @param id sku销售属性&值主键
     * @return sku销售属性&值
     */
    public SkuSaleAttrValue selectSkuSaleAttrValueById(Long id);

    /**
     * 查询sku销售属性&值列表
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return sku销售属性&值集合
     */
    public List<SkuSaleAttrValue> selectSkuSaleAttrValueList(SkuSaleAttrValue skuSaleAttrValue);

    /**
     * 新增sku销售属性&值
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */
    public int insertSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue);

    /**
     * 修改sku销售属性&值
     * 
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */
    public int updateSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue);

    /**
     * 批量删除sku销售属性&值
     * 
     * @param ids 需要删除的sku销售属性&值主键集合
     * @return 结果
     */
    public int deleteSkuSaleAttrValueByIds(Long[] ids);

    /**
     * 删除sku销售属性&值信息
     * 
     * @param id sku销售属性&值主键
     * @return 结果
     */
    public int deleteSkuSaleAttrValueById(Long id);

    /**
     * 导出sku销售属性&值列表
     */
    void export(List<SkuSaleAttrValue> list, HttpServletResponse response);

    List<SkuSaleAttrValue> getSkuSaleAttrValueBySkuIds(List<Long> skuIds);


    List<SkuSaleAttrValue> getSkuSaleAttrValueBySkuId(Long skuId);
}
