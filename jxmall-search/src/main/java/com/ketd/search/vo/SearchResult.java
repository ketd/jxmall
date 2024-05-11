package com.ketd.search.vo;

import com.ketd.common.domain.search.SkuEsModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.search.vo
 * @Author: ketd
 * @CreateTime: 2024-05-09  13:46
 */
@Data
public class SearchResult {

    @Schema(description = "商品")
    private List<SkuEsModel>  products;

    @Schema(description = "页码")
    private  Integer pageNum;

    @Schema(description = "总页数")
    private Long totalPages;

    @Schema(description = "总条数")
    private Long total;
    @Schema(description = "分类")
    private List<CatalogIdVO> catalogs;

    @Schema(description = "品牌")
    private List<BrandVO> brands;

    @Schema(description = "属性")
    private List<AttrVo> attrs;


    @Data
    public  static  class  CatalogIdVO{
         @Schema(description = "id")
         private Long catalogId;
         @Schema(description = "name")
         private String catalogName;

    }
    @Data
    public  static  class BrandVO{
         @Schema(description = "id")
         private Long brandId;
         @Schema(description = "name")
         private String brandName;

         @Schema(description = "图片")
         private String imgUrl;
    }
    @Data
    public  static  class AttrVo{
         @Schema(description = "属性id")
         private Long attrId;
         @Schema(description = "属性名")
         private String attrName;
         @Schema(description = "属性值")
         private String attrValue;
    }
}
