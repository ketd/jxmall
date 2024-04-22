package com.ketd.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.ware.vo
 * @Author: ketd
 * @CreateTime: 2024-04-21  20:20
 */
@Data
public class PurchaseDoneVo {

    private Long id;

    private List<PurchaseItemDoneVo> items;
}
