package com.ketd.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.product.vo
 * @Author: ketd
 * @CreateTime: 2024-04-20  11:35
 */

@NoArgsConstructor
@Data
public class SpuSaveVo {


    @JsonProperty("spuName")
    private String spuName;
    @JsonProperty("spuDescription")
    private String spuDescription;
    @JsonProperty("catalogId")
    private Long catalogId;
    @JsonProperty("brandId")
    private Long brandId;
    @JsonProperty("weight")
    private Double weight;
    @JsonProperty("publishStatus")
    private Integer publishStatus;
    @JsonProperty("decript")
    private List<String> decript;
    @JsonProperty("images")
    private List<String> images;
    @JsonProperty("bounds")
    private BoundsVO bounds;
    @JsonProperty("baseAttrs")
    private List<BaseAttrsVO> baseAttrs;
    @JsonProperty("skus")
    private List<SkusVO> skus;

    @NoArgsConstructor
    @Data
    public static class BoundsVO {
        @JsonProperty("buyBounds")
        private BigDecimal buyBounds;
        @JsonProperty("growBounds")
        private BigDecimal growBounds;
    }

    @NoArgsConstructor
    @Data
    public static class BaseAttrsVO {
        @JsonProperty("attrId")
        private Long attrId;
        @JsonProperty("attrValues")
        private String attrValues;
        @JsonProperty("showDesc")
        private Long showDesc;
    }

    @NoArgsConstructor
    @Data
    public static class SkusVO {
        @JsonProperty("attr")
        private List<AttrVO> attr;
        @JsonProperty("skuName")
        private String skuName;
        @JsonProperty("price")
        private BigDecimal price;
        @JsonProperty("skuTitle")
        private String skuTitle;
        @JsonProperty("skuSubtitle")
        private String skuSubtitle;
        @JsonProperty("images")
        private List<ImagesVO> images;
        @JsonProperty("descar")
        private List<String> descar;
        @JsonProperty("fullCount")
        private Long fullCount;
        @JsonProperty("discount")
        private BigDecimal discount;
        @JsonProperty("countStatus")
        private Integer countStatus;
        @JsonProperty("fullPrice")
        private BigDecimal fullPrice;
        @JsonProperty("reducePrice")
        private BigDecimal reducePrice;
        @JsonProperty("priceStatus")
        private Integer priceStatus;
        @JsonProperty("memberPrice")
        private List<MemberPriceVO> memberPrice;

        @NoArgsConstructor
        @Data
        public static class AttrVO {
            @JsonProperty("attrId")
            private Long attrId;
            @JsonProperty("attrName")
            private String attrName;
            @JsonProperty("attrValue")
            private String attrValue;
        }

        @NoArgsConstructor
        @Data
        public static class ImagesVO {
            @JsonProperty("imgUrl")
            private String imgUrl;
            @JsonProperty("defaultImg")
            private Long defaultImg;
        }

        @NoArgsConstructor
        @Data
        public static class MemberPriceVO {
            @JsonProperty("id")
            private Long id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("price")
            private BigDecimal price;
        }
    }
}
