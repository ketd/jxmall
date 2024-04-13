package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * spu信息介绍对象 pms_spu_info_desc
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_spu_info_desc")
@Data
public class SpuInfoDesc  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "商品id")
        @Schema(description =  "商品id")
        private Long spuId;

        @ExcelProperty(value = "商品介绍")
        @Schema(description =  "商品介绍")
        private String decript;


}
