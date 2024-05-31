package com.ketd.common.domain.search;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.common.domain.search
 * @Author: ketd
 * @CreateTime: 2024-04-22  18:26
 */
@Data
public class SkuEsModel {

    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;
    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Attribute> attrs;

    @Data
    public static class Attribute {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}