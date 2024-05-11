package com.ketd.ware.vo;

import lombok.Data;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.ware.vo
 * @Author: ketd
 * @CreateTime: 2024-04-21  20:19
 */
@Data
public class PurchaseItemDoneVo {

    private Long itemId;
    private Long status;
    private String reason;

}
