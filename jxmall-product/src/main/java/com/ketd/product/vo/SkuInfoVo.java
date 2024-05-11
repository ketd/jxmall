package com.ketd.product.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.product.vo
 * @Author: ketd
 * @CreateTime: 2024-04-27  16:49
 */
@Data
public class SkuInfoVo {

    @Schema(description =  "skuId")
    private Long skuId;


    @Schema(description =  "spuId")
    private Long spuId;


    @Schema(description =  "sku名称")
    private String skuName;


    @Schema(description =  "sku介绍描述")
    private String skuDesc;


    @Schema(description =  "所属分类id")
    private Long catalogId;


    @Schema(description =  "品牌id")
    private Long brandId;

    @Schema(description =  "品牌名称")
    private String brandName;


    @Schema(description =  "默认图片")
    private String skuDefaultImg;

    @Schema(description =  "标题")
    private String skuTitle;


    @Schema(description =  "副标题")
    private String skuSubtitle;


    @Schema(description =  "价格")
    private BigDecimal price;


    @Schema(description =  "销量")
    private Long saleCount;

}
