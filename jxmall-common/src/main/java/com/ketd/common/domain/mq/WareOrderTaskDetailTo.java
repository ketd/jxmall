package com.ketd.common.domain.mq;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.domain.mq
 * @Author: ketd
 * @CreateTime: 2024-06-02  17:08
 */
@Data
public class WareOrderTaskDetailTo {

    private Long id;
    private Long skuId;
    private String skuName;
    private Integer skuNum;
    private Long taskId;
    private Long wareId;
    private Integer lockStatus;

}
