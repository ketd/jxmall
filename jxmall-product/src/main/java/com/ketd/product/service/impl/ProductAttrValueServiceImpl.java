package com.ketd.product.service.impl;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.product.domain.Attr;
import com.ketd.product.vo.SpuSaveVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.ProductAttrValueMapper;
import com.ketd.product.domain.ProductAttrValue;
import com.ketd.product.service.IProductAttrValueService;
import org.springframework.transaction.annotation.Transactional;


/**
 * spu属性值Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue> implements IProductAttrValueService {

    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Autowired
    private AttrServiceImpl  attrService;


    /**
     * 查询spu属性值
     *
     * @param id spu属性值主键
     * @return spu属性值
     */
    @Override
    public ProductAttrValue selectProductAttrValueById(Long id)
    {
        return productAttrValueMapper.selectById(id);
    }



    /**
     * 查询spu属性值列表
     *
     * @param productAttrValue spu属性值
     * @return spu属性值
     */
    @Override
    public List<ProductAttrValue> selectProductAttrValueList(ProductAttrValue productAttrValue)
    {
        QueryWrapper<ProductAttrValue> queryWrapper = new QueryWrapper<>(productAttrValue);
        return productAttrValueMapper.selectList(queryWrapper);
    }

    /**
     * 新增spu属性值
     *
     * @param productAttrValue spu属性值
     * @return 结果
     */

    @Override
    public int insertProductAttrValue(ProductAttrValue productAttrValue) {
        return productAttrValueMapper.insert(productAttrValue);
    }





    /**
     * 修改spu属性值
     *
     * @param productAttrValue spu属性值
     * @return 结果
     */

    @Override
    public int updateProductAttrValue(ProductAttrValue productAttrValue) {
        return productAttrValueMapper.updateById(productAttrValue);
    }

    /**
     * 批量删除spu属性值
     *
     * @param ids 需要删除的spu属性值主键集合
     * @return 结果
     */
    @Override
    public int deleteProductAttrValueByIds(Long[] ids) {
        return productAttrValueMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除spu属性值信息
     *
     * @param id spu属性值主键
     * @return 结果
     */
    @Override
    public int deleteProductAttrValueById(Long id) {
        return productAttrValueMapper.deleteById(id);
    }


    /**
     * 导出spu属性值列表
     */
    @Override
    public void export(List<ProductAttrValue> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), ProductAttrValue.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void saveProductAttrValue(Long id, List<SpuSaveVo.BaseAttrsVO> baseAttrs) {
        List<ProductAttrValue> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValue productAttrValue = new ProductAttrValue();
            productAttrValue.setAttrId(attr.getAttrId());
            Attr attrId = attrService.getById(attr.getAttrId());
            productAttrValue.setAttrName(attrId.getAttrName());
            productAttrValue.setAttrValue(attr.getAttrValues());
            productAttrValue.setQuickShow(attr.getShowDesc());
            productAttrValue.setSpuId(id);
            return productAttrValue;
        }).collect(Collectors.toList());
        this.saveBatch(collect);

    }

    @Override
    public List<ProductAttrValue> baseAttrListForSpu(Long id) {

        ProductAttrValue  productAttrValue = new ProductAttrValue();
        productAttrValue.setSpuId(id);
        List<ProductAttrValue> list = this.list(new QueryWrapper<>(productAttrValue));
        return list;
    }
}