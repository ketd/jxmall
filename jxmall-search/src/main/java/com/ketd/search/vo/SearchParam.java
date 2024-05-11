package com.ketd.search.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.search.vo
 * @Author: ketd
 * @CreateTime: 2024-05-09  13:22
 */

@Data
public class SearchParam {


    @Schema(description = "检索关键字")
    private String keyword;

    @Schema(description = "分类id")
    private Long catalogId;

    @Schema(description = "排序字段")
    private String sort;

    /*
     * 过滤条件
     * 1. hasStock  0 or 1 是否有货
     * 2. skuPrice 1_500 _200 3000_ 价格区间
     * 3. brandId 1 2 3 品牌
     * 4. cat3Id 1 2 3 三级分类
     * 5. attrs 1_100 2_200 属性
     **/

    @Schema(description = "是否有货")
    private Integer hasStock;

    @Schema(description = "价格区间")
    private String skuPrice;

    @Schema(description = "品牌id")
    private List<Long> brandId;


    @Schema(description = "属性")
    private List<attrQuery> attrs;

    @Schema(description = "页码")
    private Integer pageNum = 1;

    @Schema(description = "每页显示条数")
    private Integer pageSize = 20;

    @Schema(description = "是否需要分页")
    private Boolean needPage;


    @Data
    public  static  class  attrQuery{
        @Schema(description = "id")
        private Long attrId;
        @Schema(description = "name")
        private List<String> attrNames;

    }


}
