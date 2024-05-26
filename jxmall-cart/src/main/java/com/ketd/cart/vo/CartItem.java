package com.ketd.cart.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.cart.vo
 * @Author: ketd
 * @CreateTime: 2024-05-18  16:37
 */

@Data
public class CartItem {



    @Schema(description =  "skuId")
    private Long skuId;

    @Schema(description =  "sku标题")
    private String skuTitle;

    @Schema(description =  "图片")
    private String image;

    @Schema(description =  "品牌名")
    private String brandName;

    @Schema(description =  "sku价格")
    private BigDecimal price;


    @Schema(description =  "sku数量")
    private Long count;


    @Schema(description =  "sku销售属性")
    private String skuAttr;



    @Schema(description =  "是否选中")
    private Boolean check;


    @Schema(description =  "优惠详情")
    private List<promotionInfo> promotionMessages;



    @Data
    private static class promotionInfo{

        @Schema(description =  "优惠金额")
        private BigDecimal amount;

        @Schema(description =  "优惠名称")
        private String name;

    }

}
