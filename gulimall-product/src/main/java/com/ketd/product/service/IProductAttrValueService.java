package com.ketd.product.service;


import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketd.product.domain.ProductAttrValue;


/**
 * spu属性值Service接口
 * 
 * @author ketd
 * @date 2024-04-12
 */
public interface IProductAttrValueService  extends IService<ProductAttrValue> {
    /**
     * 查询spu属性值
     * 
     * @param id spu属性值主键
     * @return spu属性值
     */
    public ProductAttrValue selectProductAttrValueById(Long id);

    /**
     * 查询spu属性值列表
     * 
     * @param productAttrValue spu属性值
     * @return spu属性值集合
     */
    public List<ProductAttrValue> selectProductAttrValueList(ProductAttrValue productAttrValue);

    /**
     * 新增spu属性值
     * 
     * @param productAttrValue spu属性值
     * @return 结果
     */
    public int insertProductAttrValue(ProductAttrValue productAttrValue);

    /**
     * 修改spu属性值
     * 
     * @param productAttrValue spu属性值
     * @return 结果
     */
    public int updateProductAttrValue(ProductAttrValue productAttrValue);

    /**
     * 批量删除spu属性值
     * 
     * @param ids 需要删除的spu属性值主键集合
     * @return 结果
     */
    public int deleteProductAttrValueByIds(Long[] ids);

    /**
     * 删除spu属性值信息
     * 
     * @param id spu属性值主键
     * @return 结果
     */
    public int deleteProductAttrValueById(Long id);

    /**
     * 导出spu属性值列表
     */
    void export(List<ProductAttrValue> list, HttpServletResponse response);
}
