package com.ketd.product.vo;


import com.ketd.product.domain.Attr;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.product.vo
 * @Author: ketd
 * @CreateTime: 2024-04-19  11:03
 */
@Data
public class AttrGroupWithAttrsVo {

    @Schema(description =  "分组id")
    private Long attrGroupId;


    @Schema(description =  "组名")
    private String attrGroupName;


    @Schema(description =  "排序")
    private Long sort;


    @Schema(description =  "描述")
    private String descript;


    @Schema(description =  "组图标")
    private String icon;


    @Schema(description =  "所属分类id")
    private Long catelogId;


    private List<Attr> attrs;


}
