package com.ketd.order.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-28  17:23
 */
@Data
public class SubmitOrderVo {
    private Long addrId;
    private Integer payType;
    private List<SkuCountVo> skuCountVoList;
    private String orderToken;
    private String remark;
}
