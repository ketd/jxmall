package com.ketd.product.vo;

import com.ketd.product.domain.Category;
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



    private Long attrId;

    private String attrName;

    private Long searchType;

    private Long valueType;

    private String icon;

    private String valueSelect;

    private Long attrType;

    private Long enable;

    private Long catelogId;

    private Long attrGroupId;

    private Long showDesc;

    private Long[] catelogPath;

}
