package com.ketd.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.ware.vo
 * @Author: ketd
 * @CreateTime: 2024-05-29  19:05
 */
@Data
public class SkuWareHasStock {
    private Long skuId;
    private List<Long> wareIds;
    private Integer count;
}
