package com.ketd.product.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ketd.common.api.ware.WareSkuOpenFeignApi;
import com.ketd.common.domain.ware.WareSkuTO;
import com.ketd.common.result.Result;
import com.ketd.product.mapper.BrandMapper;
import com.ketd.product.utils.RedisUtil;
import com.ketd.product.vo.SkuInfoVo;
import com.ketd.product.vo.SkuItemVo;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.SkuInfoMapper;
import com.ketd.product.domain.SkuInfo;
import com.ketd.product.service.ISkuInfoService;


/**
 * sku信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements ISkuInfoService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private WareSkuOpenFeignApi  wareSkuOpenFeignApi;

    /**
     * 查询sku信息
     *
     * @param skuId sku信息主键
     * @return sku信息
     */
    @Override
    public Result<?> selectSkuInfoBySkuId(Long skuId) {
        String key = "skuInfo:" + skuId;

        SkuInfo skuInfo;
        skuInfo = redisUtil.getJson(key, new TypeReference<>() {
        });
        RLock lock = redisson.getLock("getSkuInfo_lock"+skuId);


        if (skuInfo != null) {
            return Result.ok(skuInfo);
        } else {
            lock.lock(30, TimeUnit.SECONDS);
            try {

                skuInfo = skuInfoMapper.selectById(skuId);
                redisUtil.setJson(key, skuInfo, 10000);
            } finally {
                lock.unlock();
            }
        }

        return Result.ok(skuInfo);
    }


    /**
     * 查询sku信息列表
     *
     * @param skuInfo sku信息
     * @return sku信息
     */
    @Override
    public List<SkuInfo> selectSkuInfoList(SkuInfo skuInfo) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>(skuInfo);
        return skuInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增sku信息
     *
     * @param skuInfo sku信息
     * @return 结果
     */

    @Override
    public int insertSkuInfo(SkuInfo skuInfo) {
        return skuInfoMapper.insert(skuInfo);
    }


    /**
     * 修改sku信息
     *
     * @param skuInfo sku信息
     * @return 结果
     */

    @Override
    public int updateSkuInfo(SkuInfo skuInfo) {
        return skuInfoMapper.updateById(skuInfo);
    }

    /**
     * 批量删除sku信息
     *
     * @param skuIds 需要删除的sku信息主键集合
     * @return 结果
     */
    @Override
    public int deleteSkuInfoBySkuIds(Long[] skuIds) {
        return skuInfoMapper.deleteBatchIds(Arrays.asList(skuIds));
    }

    /**
     * 删除sku信息信息
     *
     * @param skuId sku信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuInfoBySkuId(Long skuId) {
        return skuInfoMapper.deleteById(skuId);
    }


    /**
     * 导出sku信息列表
     */
    @Override
    public void export(List<SkuInfo> list, HttpServletResponse response) {

        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表" + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            EasyExcel.write(response.getOutputStream(), SkuInfo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insert(skuInfo);
    }

    @Override
    public List<SkuInfo> getSkuInfoBySpuId(Long id) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSpuId(id);
        return this.list(new QueryWrapper<>(skuInfo));
    }

    @Override
    public Result<?> getSkuInfos(Long[] skuIds) {
        List<SkuInfo> skuInfoList = skuInfoMapper.selectBatchIds(Arrays.asList(skuIds));
        List<Long> branIds = skuInfoList.stream().map(SkuInfo::getBrandId).distinct().toList();
        List<String> brandNames = brandMapper.selectBatchIds(branIds).stream().map(com.ketd.product.domain.Brand::getName).toList();
        List<SkuInfoVo> skuInfoVoList = skuInfoList.stream().map(skuInfo -> {
            SkuInfoVo skuInfoVo = new SkuInfoVo();
            BeanUtils.copyProperties(skuInfo, skuInfoVo);
            skuInfoVo.setBrandName(brandNames.get(branIds.indexOf(skuInfo.getBrandId())));
            return skuInfoVo;
        }).toList();
        return Result.ok(skuInfoVoList);
    }

    @Override
    public Result<?> addTest(Long spuId) {
        try {
            List<SkuInfo> skuInfoList = this.getSkuInfoBySpuId(spuId);
            List<WareSkuTO>  wareSkuTOList = new ArrayList<>();
            Random random = new Random();
            for(SkuInfo skuInfo : skuInfoList){
                WareSkuTO wareSkuTO=new WareSkuTO();
                wareSkuTO.setSkuId(skuInfo.getSkuId());
                wareSkuTO.setWareId(1L);
                wareSkuTO.setStock((long) (random.nextInt(9999) + 1));
                wareSkuTO.setSkuName(skuInfo.getSkuName());
                wareSkuTO.setStockLocked(1L);
                wareSkuTOList.add(wareSkuTO);
            }

            wareSkuOpenFeignApi.addList(wareSkuTOList);
            return Result.ok(null);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

}