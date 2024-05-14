package com.ketd.product.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.product.domain.SkuInfo;
import com.ketd.product.mapper.SkuInfoMapper;
import com.ketd.product.vo.SkuItemSaleVo;
import com.ketd.product.vo.SkuItemVo;
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


    @Autowired
    private SkuInfoMapper  skuInfoMapper;

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

    @Override
    public List<SkuSaleAttrValue> getSkuSaleAttrValueBySkuIds(List<Long> skuIds) {

        List<SkuSaleAttrValue> skuSaleAttrValueList=new ArrayList<>();
        for (Long skuId : skuIds) {
            List<SkuSaleAttrValue> skuSaleAttrValueList1 =  skuSaleAttrValueMapper.findAllBySkuId(skuId);
            if(skuSaleAttrValueList1!=null){
                skuSaleAttrValueList.addAll(skuSaleAttrValueList1);
            }

        }
        return skuSaleAttrValueList;
    }

    @Override
    public List<SkuSaleAttrValue> getSkuSaleAttrValueBySkuId(Long skuId) {
        return skuSaleAttrValueMapper.findAllBySkuId(skuId);

    }

    @Override
    public List<SkuItemSaleVo> getSkuSaleAttrValueBySpuId(Long spuId) {
       /* // 第一步：通过spuId获取SKU信息
        List<SkuInfo> skuInfoList = skuInfoMapper.findSkuIdBySpuId(spuId);
        if (skuInfoList == null || skuInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        // 提取skuIds
        List<Long> skuIds = skuInfoList.stream()
                .map(SkuInfo::getSkuId)
                .collect(Collectors.toList());

        // 第二步：通过skuIds获取销售属性
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuSaleAttrValueMapper.findSaleAttrBySpuIds(skuIds);
        if (skuSaleAttrValueList == null || skuSaleAttrValueList.isEmpty()) {
            return Collections.emptyList();
        }

        // 第三步：分组并汇总销售属性数据
        Map<Long, SkuItemVo.SkuItemSaleVo> saleAttrMap = new HashMap<>();
        for (SkuSaleAttrValue saleAttr : skuSaleAttrValueList) {
            Long attrId = saleAttr.getAttrId();
            SkuItemVo.SkuItemSaleVo skuItemSaleVo = saleAttrMap.computeIfAbsent(attrId, id -> {
                SkuItemVo.SkuItemSaleVo newVo = new SkuItemVo.SkuItemSaleVo();
                newVo.setAttrId(attrId);
                newVo.setAttrName(saleAttr.getAttrName());
                newVo.setAttrValues(new ArrayList<>());
                return newVo;
            });
            // 添加唯一的属性值
            if (!skuItemSaleVo.getAttrValues().contains(saleAttr.getAttrValue())) {
                skuItemSaleVo.getAttrValues().add(saleAttr.getAttrValue());
            }
        }

        // 转换map的值为列表并返回
        return new ArrayList<>(saleAttrMap.values());*/

        return skuSaleAttrValueMapper.selectSaleAttrBySpuId(spuId);

    }

}