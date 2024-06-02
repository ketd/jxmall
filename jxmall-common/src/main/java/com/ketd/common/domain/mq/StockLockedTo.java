package com.ketd.common.domain.mq;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.domain.mq
 * @Author: ketd
 * @CreateTime: 2024-06-02  16:49
 */
@Data
public class StockLockedTo {
    private Long wareOrderTaskId;
    private WareOrderTaskDetailTo wareOrderTaskDetail;
}
