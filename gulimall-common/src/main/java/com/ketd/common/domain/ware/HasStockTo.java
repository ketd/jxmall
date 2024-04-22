package com.ketd.common.domain.ware;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.ware.vo
 * @Author: ketd
 * @CreateTime: 2024-04-22  20:46
 */
@Data
public class HasStockTo {
     private Long skuId;
     private Boolean hasStock;
}
