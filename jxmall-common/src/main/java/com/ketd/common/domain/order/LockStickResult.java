package com.ketd.common.domain.order;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.domain.order
 * @Author: ketd
 * @CreateTime: 2024-05-29  18:40
 */

@Data
public class LockStickResult {
    private Long skuId;
    private Long wareId;
    private Integer count;
    private Boolean lockStock;
}
