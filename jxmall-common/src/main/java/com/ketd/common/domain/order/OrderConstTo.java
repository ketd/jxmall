package com.ketd.common.domain.order;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-27  23:13
 */
@Data
public class OrderConstTo implements Serializable {

    @ExcelProperty(value = "商品id")
    private Long skuId;
    @ExcelProperty(value = "数量")
    private BigDecimal skuConst;
}
