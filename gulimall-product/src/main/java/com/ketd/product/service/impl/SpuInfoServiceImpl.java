package com.ketd.product.service.impl;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketd.common.api.coupon.*;
import com.ketd.common.api.search.SearchOpenFeignApi;
import com.ketd.common.api.ware.WareSkuOpenFeignApi;
import com.ketd.common.domain.coupon.MemberPriceTO;
import com.ketd.common.domain.coupon.SkuFullReductionTO;
import com.ketd.common.domain.coupon.SkuLadderTO;
import com.ketd.common.domain.coupon.SpuBoundsTO;
import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.domain.ware.HasStockTo;
import com.ketd.common.result.Result;
import com.ketd.product.domain.*;
import com.ketd.product.vo.SpuSaveVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import com.ketd.product.mapper.SpuInfoMapper;
import com.ketd.product.service.ISpuInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.ketd.common.excel.excel.extracted;


/**
 * spu信息Service业务层处理
 *
 * @author ketd
 * @date 2024-04-12
 */
@Service
@Primary
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {


    private final SpuInfoMapper spuInfoMapper;

    private final SpuInfoDescServiceImpl spuInfoDescService;

    private final SpuImagesServiceImpl spuImagesService;

    private final SkuInfoServiceImpl skuInfoServiceImpl;

    private final ProductAttrValueServiceImpl productAttrValueService;

    private final SkuImagesServiceImpl skuImagesService;

    private final SkuSaleAttrValueServiceImpl skuSaleAttrValueService;

    private final SpuBoundsOpenFeignApi spuBoundsOpenFeignApi;

    private final SkuFullReductionOpenFeignApi skuFullReductionOpenFeignApi;

    private final SkuLadderOpenFeignApi skuLadderOpenFeignApi;

    private final MemberPriceOpenFeignApi memberPriceOpenFeignApi;


    private final BrandServiceImpl brandService;

    private final  CategoryServiceImpl  categoryService;

    private final  AttrServiceImpl attrService;

    private final WareSkuOpenFeignApi   wareSkuOpenFeignApi;

    private final SearchOpenFeignApi searchOpenFeignApi;

    @Autowired
    private SpuInfoServiceImpl(
            SpuInfoMapper spuInfoMapper,
            SpuInfoDescServiceImpl spuInfoDescService,
            SpuImagesServiceImpl spuImagesService,
            SkuInfoServiceImpl skuInfoServiceImpl,
            ProductAttrValueServiceImpl productAttrValueService,
            SkuImagesServiceImpl skuImagesService,
            SkuSaleAttrValueServiceImpl skuSaleAttrValueService,
            SpuBoundsOpenFeignApi spuBoundsOpenFeignApi,
            SkuFullReductionOpenFeignApi skuFullReductionOpenFeignApi,
            SkuLadderOpenFeignApi skuLadderOpenFeignApi,
            MemberPriceOpenFeignApi memberPriceOpenFeignApi,
            BrandServiceImpl brandService,
            CategoryServiceImpl categoryService,
            AttrServiceImpl attrService,
            WareSkuOpenFeignApi wareSkuOpenFeignApi,
            SearchOpenFeignApi  searchOpenFeignApi
    ) {
        this.spuInfoMapper = spuInfoMapper;
        this.spuInfoDescService = spuInfoDescService;
        this.spuImagesService = spuImagesService;
        this.skuInfoServiceImpl = skuInfoServiceImpl;
        this.productAttrValueService = productAttrValueService;
        this.skuImagesService = skuImagesService;
        this.skuSaleAttrValueService = skuSaleAttrValueService;
        this.spuBoundsOpenFeignApi = spuBoundsOpenFeignApi;
        this.skuFullReductionOpenFeignApi = skuFullReductionOpenFeignApi;
        this.skuLadderOpenFeignApi = skuLadderOpenFeignApi;
        this.memberPriceOpenFeignApi = memberPriceOpenFeignApi;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.attrService = attrService;
        this.wareSkuOpenFeignApi = wareSkuOpenFeignApi;
        this.searchOpenFeignApi = searchOpenFeignApi;
    }


    /**
     * 查询spu信息
     *
     * @param id spu信息主键
     * @return spu信息
     */
    @Override
    public SpuInfo selectSpuInfoById(Long id) {
        return spuInfoMapper.selectById(id);
    }


    /**
     * 查询spu信息列表
     *
     * @param spuInfo spu信息
     * @return spu信息
     */
    @Override
    public List<SpuInfo> selectSpuInfoList(SpuInfo spuInfo) {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>(spuInfo);
        return spuInfoMapper.selectList(queryWrapper);
    }

    /**
     * 新增spu信息
     *
     * @param spuInfo spu信息
     * @return 结果
     */

    @Override
    public int insertSpuInfo(SpuInfo spuInfo) {
        return spuInfoMapper.insert(spuInfo);
    }


    /**
     * 修改spu信息
     *
     * @param spuInfo spu信息
     * @return 结果
     */

    @Override
    public int updateSpuInfo(SpuInfo spuInfo) {
        return spuInfoMapper.updateById(spuInfo);
    }

    /**
     * 批量删除spu信息
     *
     * @param ids 需要删除的spu信息主键集合
     * @return 结果
     */
    @Override
    public int deleteSpuInfoByIds(Long[] ids) {
        return spuInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除spu信息信息
     *
     * @param id spu信息主键
     * @return 结果
     */
    @Override
    public int deleteSpuInfoById(Long id) {
        return spuInfoMapper.deleteById(id);
    }


    /**
     * 导出spu信息列表
     */
    @Override
    public void export(List<SpuInfo> list, HttpServletResponse response) {

        extracted(list, response, SpuInfo.class);
    }


    @Transactional
    @Override
    public Result<?> saveSpuInfo(SpuSaveVo spuSaveVo) {
        try {
            //1.保存spu基本信息
            SpuInfo spuBaseInfo = new SpuInfo();
            BeanUtils.copyProperties(spuSaveVo, spuBaseInfo);
            spuBaseInfo.setCreateTime(new Date());
            spuBaseInfo.setUpdateTime(new Date());
            this.saveBaseSpuInfo(spuBaseInfo);
            //2.1保存spu的描述图片
            List<String> decript = spuSaveVo.getDecript();
            SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
            spuInfoDesc.setSpuId(spuBaseInfo.getId());
            spuInfoDesc.setDecript(String.join(",", decript));
            spuInfoDescService.insertSpuInfoDesc(spuInfoDesc);

            //2.2保存spu的图片集
            List<String> images = spuSaveVo.getImages();
            spuImagesService.saveImages(spuBaseInfo.getId(), images);

            this.updateById(spuBaseInfo);

            //3.保存spu的规格参数
            List<SpuSaveVo.BaseAttrsVO> baseAttrs = spuSaveVo.getBaseAttrs();
            productAttrValueService.saveProductAttrValue(spuBaseInfo.getId(), baseAttrs);

            //4.保存spu的积分信息
            SpuSaveVo.BoundsVO bounds = spuSaveVo.getBounds();
            SpuBoundsTO spuBoundTo = new SpuBoundsTO();
            BeanUtils.copyProperties(bounds, spuBoundTo);
            spuBoundTo.setSpuId(spuBaseInfo.getId());
            spuBoundsOpenFeignApi.add(spuBoundTo);


            //5.保存spu的sku信息
            List<SpuSaveVo.SkusVO> skus = spuSaveVo.getSkus();
            if (skus != null && !skus.isEmpty()) {

                skus.forEach(item -> {

                    String defaultImg = "";
                    for (SpuSaveVo.SkusVO.ImagesVO img : item.getImages()) {
                        if (img.getDefaultImg() == 1) {
                            defaultImg = img.getImgUrl();
                        }
                    }
                    SkuInfo skuInfo = new SkuInfo();
                    BeanUtils.copyProperties(spuBaseInfo, skuInfo);
                    skuInfo.setBrandId(spuBaseInfo.getBrandId());
                    skuInfo.setCatalogId(spuBaseInfo.getCatalogId());
                    skuInfo.setSaleCount(0L);
                    skuInfo.setSpuId(spuBaseInfo.getId());
                    skuInfo.setSkuDefaultImg(defaultImg);
                    skuInfo.setSkuDesc(spuInfoDesc.getDecript());

                    skuInfoServiceImpl.saveSkuInfo(skuInfo);

                    Long skuId = skuInfo.getSkuId();


                    List<SkuImages> skuImages = item.getImages().stream().map(img -> {
                        SkuImages skuImage = new SkuImages();
                        skuImage.setSkuId(skuId);
                        skuImage.setDefaultImg(img.getDefaultImg());
                        skuImage.setImgUrl(img.getImgUrl());
                        return skuImage;
                    }).filter(img -> {
                        return !StringUtils.hasLength(img.getImgUrl());
                    }).toList();

                    skuImagesService.saveBatch(skuImages);

                    List<SpuSaveVo.SkusVO.AttrVO> attrs = item.getAttr();
                    List<SkuSaleAttrValue> skuSaleAttrValueList = attrs.stream().map(attrVO -> {
                        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
                        BeanUtils.copyProperties(attrVO, skuSaleAttrValue);
                        skuSaleAttrValue.setSkuId(skuId);
                        return skuSaleAttrValue;
                    }).collect(Collectors.toList());

                    skuSaleAttrValueService.saveBatch(skuSaleAttrValueList);


                    SkuLadderTO skuLadderTO = new SkuLadderTO();
                    BeanUtils.copyProperties(item, skuLadderTO);
                    skuLadderTO.setSkuId(skuId);
                    if (skuLadderTO.getFullCount() >= 0) {
                        skuLadderOpenFeignApi.add(skuLadderTO);
                    }


                    SkuFullReductionTO skuFullReductionTO = new SkuFullReductionTO();
                    BeanUtils.copyProperties(item, skuFullReductionTO);
                    skuFullReductionTO.setSkuId(skuId);
                    if (skuFullReductionTO.getFullPrice().compareTo(BigDecimal.ZERO) == 1) {
                        skuFullReductionOpenFeignApi.add(skuFullReductionTO);
                    }


                    List<SpuSaveVo.SkusVO.MemberPriceVO> memberPrice = item.getMemberPrice();
                    List<MemberPriceTO> memberPriceTOList = memberPrice.stream().map(memberPriceVO -> {
                        MemberPriceTO memberPriceTO = new MemberPriceTO();
                        memberPriceTO.setMemberPrice(memberPriceVO.getPrice());
                        memberPriceTO.setMemberLevelId(memberPriceVO.getId());
                        memberPriceTO.setMemberLevelName(memberPriceVO.getName());
                        memberPriceTO.setSkuId(skuId);
                        return memberPriceTO;
                    }).filter(memberPriceTO -> {
                        return memberPriceTO.getMemberPrice().compareTo(BigDecimal.ZERO) == 1;
                    }).toList();
                    for (MemberPriceTO memberPriceTO : memberPriceTOList) {
                        memberPriceOpenFeignApi.add(memberPriceTO);
                    }


                });
            }

            return Result.ok(null);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        //6.保存spu的营销信息


    }

    @Override
    public void saveBaseSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);
    }

    /*
     * 描述: 商品上架
     * @description:
     * @author: ketd
     * @date: 2024/4/22 18:33
     * @param null
     * @return: null
     **/
    // 这段代码的主要功能是使用搜索微服务（searchOpenFeignApi）将商品数据（SkuEsModel）上架到搜索系统中，下面是加上中文注释后的代码：
    @Override
    public Result<?> up(Long[] supIds) {
        for (Long id : supIds) {
            //            查询出 spu 下的所有 sku 信息
            List<SkuInfo> skuInfoList = skuInfoServiceImpl.getSkuInfoBySpuId(id);
            //            查询出 spu 下的基本属性
            List<ProductAttrValue> productBaesAttrValueList = productAttrValueService.baseAttrListForSpu(id);
            //            取出所有属性的 id
            List<Long> attrIds =  productBaesAttrValueList.stream().map(ProductAttrValue::getAttrId).toList();
            //            查询出需要被检索的属性
            List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIds);
            //            利用集合去重，将需要检索的属性 id 集合转换为 set
            Set<Long> idSet = new HashSet<>(searchAttrIds);
            //            查询出所有需要被检索的属性
            List<SkuEsModel.Attribute>  attributes =   productBaesAttrValueList.stream().filter(productAttrValue -> {
                //                    判断当前的属性是否在需要检索的属性集合中
                return idSet.contains(productAttrValue.getAttrId());
            }).map(productAttrValue -> {
                //                    创建一个新的 SkuEsModel.Attribute 对象
                SkuEsModel.Attribute  attribute = new SkuEsModel.Attribute();
                //                    使用 BeanUtils 工具类将当前的 ProductAttrValue 对象中的属性拷贝到新的对象中
                BeanUtils.copyProperties(productAttrValue, attribute);
                //                    返回新的对象
                return   attribute;
            }).toList();



            //            查询出所有 sku 的库存信息
            List<HasStockTo>  hasStockToList =  wareSkuOpenFeignApi.hasStock(skuInfoList.stream().map(SkuInfo::getSkuId).toList()).getData();
            //            将所有返回的 HasStockTo 对象根据 skuId 进行分组，生成一个 Map，key 为 skuId，value 为库存信息
            Map<Long, Boolean>  hasStockMap =  hasStockToList.stream().collect(Collectors.toMap(  HasStockTo::getSkuId, HasStockTo::getHasStock));

            //            将所有需要上架的 SkuEsModel 对象生成出来
            List<SkuEsModel> skuEsModels = skuInfoList.stream().map(skuInfo -> {
                //                    创建一个新的 SkuEsModel 对象
                SkuEsModel skuEsModel = new SkuEsModel();
                //                    使用 BeanUtils 工具类将当前的 SkuInfo 对象中的属性拷贝到新的对象中
                BeanUtils.copyProperties(skuInfo, skuEsModel);
                //                    将当前的 sku 的价格设置为 skuEsModel 的 skuPrice 属性
                skuEsModel.setSkuPrice(skuInfo.getPrice());
                //                    将当前的 sku 的默认图片设置为 skuEsModel 的 skuImg 属性
                skuEsModel.setSkuImg(skuInfo.getSkuDefaultImg());
                //                    将当前的 sku 的热度分数设置为 0L，表示不hot
                skuEsModel.setHotScore(0L);
                //                    根据当前的 skuId，从库存信息中获取对应的库存状态
                skuEsModel.setHasStock(hasStockMap.get(skuInfo.getSkuId()));



                //                    根据当前的 brandId 查询出当前的品牌信息
                Brand  brand = brandService.getById(skuInfo.getBrandId());
                //                    将当前的品牌名称设置为 skuEsModel 的 brandName 属性
                skuEsModel.setBrandName(brand.getName());
                //                    将当前的品牌logo设置为 skuEsModel 的 brandImg 属性
                skuEsModel.setBrandImg(brand.getLogo());


                //                    根据当前的 catalogId 查询出当前的分类信息
                Category category = categoryService.getById(skuInfo.getCatalogId());
                //                    将当前的分类名称设置为 skuEsModel 的 catalogName 属性
                skuEsModel.setCatalogName(category.getName());
                //                    将当前的分类 id 设置为 skuEsModel 的 catalogId 属性
                skuEsModel.setCatalogId(category.getCatId());


                //                    将所有需要检索的属性设置为 skuEsModel 的 attrs 属性
                skuEsModel.setAttrs(attributes);


                //                    使用搜索微服务将当前的 SkuEsModel 对象上架到搜索系统中
                return skuEsModel;
            }).toList();

            //            使用搜索微服务批量上架商品
            searchOpenFeignApi.upProduct(skuEsModels);


        }

        //        返回成功
        return null;
    }


}