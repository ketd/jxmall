package com.ketd.order.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-29  18:33
 */
@Data
public class WareSkuLockTo {

    private String OrderSn;
    private List<SkuCountVo> locks;


}
