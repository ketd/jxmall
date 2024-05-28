package com.ketd.order.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.vo
 * @Author: ketd
 * @CreateTime: 2024-05-27  21:54
 */
@Data
public class OrderItemVo implements Serializable {

    private Long skuId;


    private String title;

    private String image;

    /**
     * 商品套餐属性
     */
    private String skuAttrValues;

    private BigDecimal price;

    private Integer count;

    private Boolean hasStock;


    /** 商品重量 **/
    private BigDecimal weight = new BigDecimal("0.085");
}
