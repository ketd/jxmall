package com.ketd.common.domain.ware;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class PurchaseDetailTO  implements Serializable
{


    private Long id;
    private Long purchaseId;
    private Long skuId;
    private Long skuNum;
    private BigDecimal skuPrice;
    private Long wareId;
    private Long status;


}
