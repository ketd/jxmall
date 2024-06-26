package com.ketd.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ketd.product.domain.SkuImages;
import com.ketd.product.domain.SkuInfo;
import com.ketd.product.domain.SpuInfoDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.product.vo
 * @Author: ketd
 * @CreateTime: 2024-05-13  19:36
 */
@Data
public class SkuItemVo {

    @Schema(description="商品详情")
    private SkuInfo  skuInfo;

    @Schema(description="sku是否有库存")
    private Boolean hasStock;

    @Schema(description="sku图片")
    private List<SkuImages>  skuImages;

    @Schema(description="sku销售属性")
    private List<SkuItemSaleVo>  skuItemSaleVo;


    @Schema(description="spu规格参数")
    private List<SpuItemBaseAttrVo>  spuItemBaseAttrVo;


    @Schema(description="spu介绍")
    private SpuInfoDesc  spuInfoDesc;
}

