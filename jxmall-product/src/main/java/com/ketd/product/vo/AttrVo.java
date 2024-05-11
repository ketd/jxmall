package com.ketd.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.product.vo
 * @Author: ketd
 * @CreateTime: 2024-04-16  18:34
 */
@Data
public class AttrVo {

    @Schema(description =  "属性id")
    private Long attrId;


    @Schema(description =  "属性名")
    private String attrName;


    @Schema(description =  "是否需要检索[0-不需要，1-需要]")
    private Long searchType;


    @Schema(description =  "值类型[0-为单个值，1-可以选择多个值]")
    private Long valueType;


    @Schema(description =  "属性图标")
    private String icon;


    @Schema(description =  "可选值列表[用逗号分隔]")
    private String valueSelect;


    @Schema(description =  "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Long attrType;

    @Schema(description =  "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;


    @Schema(description =  "所属分类")
    private Long catelogId;

    @Schema(description =  "所属分类名字")
    private String catelogName;

    @Schema(description =  "所属分组id")
    private Long attrGroupId;

    @Schema(description =  "所属分组名字")
    private String attrGroupName;

    @Schema(description =  "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Long showDesc;

    @Schema(description =  "分组路径")
    private Long[] catelogPath;

}
