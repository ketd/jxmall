package com.ketd.product.vo;

import lombok.Data;

import java.util.List;

@Data
    public  class SkuItemSaleVo{

        private Long attrId;

        private String attrName;

        private List<AttrValueWithSkuIdVo> attrValues;
    }
