package com.ketd.order.vo;

import com.ketd.order.domain.Order;
import lombok.Data;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-28  17:42
 */
@Data
public class SubmitOrderResponseVo {
    private Order  order;
    private Integer code;
}
