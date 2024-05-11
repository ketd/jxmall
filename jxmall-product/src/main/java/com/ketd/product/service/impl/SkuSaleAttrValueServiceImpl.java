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
import com.ketd.product.mapper.SkuSaleAttrValueMapper;
import com.ketd.product.domain.SkuSaleAttrValue;
import com.ketd.product.service.ISkuSaleAttrValueService;


/**
 * sku销售属性&值Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements ISkuSaleAttrValueService {

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;



    /**
     * 查询sku销售属性&值
     *
     * @param id sku销售属性&值主键
     * @return sku销售属性&值
     */
    @Override
    public SkuSaleAttrValue selectSkuSaleAttrValueById(Long id)
    {
        return skuSaleAttrValueMapper.selectById(id);
    }



    /**
     * 查询sku销售属性&值列表
     *
     * @param skuSaleAttrValue sku销售属性&值
     * @return sku销售属性&值
     */
    @Override
    public List<SkuSaleAttrValue> selectSkuSaleAttrValueList(SkuSaleAttrValue skuSaleAttrValue)
    {
        QueryWrapper<SkuSaleAttrValue> queryWrapper = new QueryWrapper<>(skuSaleAttrValue);
        return skuSaleAttrValueMapper.selectList(queryWrapper);
    }

    /**
     * 新增sku销售属性&值
     *
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */

    @Override
    public int insertSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue) {
        return skuSaleAttrValueMapper.insert(skuSaleAttrValue);
    }





    /**
     * 修改sku销售属性&值
     *
     * @param skuSaleAttrValue sku销售属性&值
     * @return 结果
     */

    @Override
    public int updateSkuSaleAttrValue(SkuSaleAttrValue skuSaleAttrValue) {
        return skuSaleAttrValueMapper.updateById(skuSaleAttrValue);
    }

    /**
     * 批量删除sku销售属性&值
     *
     * @param ids 需要删除的sku销售属性&值主键集合
     * @return 结果
     */
    @Override
    public int deleteSkuSaleAttrValueByIds(Long[] ids) {
        return skuSaleAttrValueMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除sku销售属性&值信息
     *
     * @param id sku销售属性&值主键
     * @return 结果
     */
    @Override
    public int deleteSkuSaleAttrValueById(Long id) {
        return skuSaleAttrValueMapper.deleteById(id);
    }


    /**
     * 导出sku销售属性&值列表
     */
    @Override
    public void export(List<SkuSaleAttrValue> list, HttpServletResponse response) {

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
            EasyExcel.write(response.getOutputStream(), SkuSaleAttrValue.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}