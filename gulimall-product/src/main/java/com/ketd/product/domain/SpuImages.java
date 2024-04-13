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
 * spu图片对象 pms_spu_images
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_spu_images")
@Data
public class SpuImages  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "图片名")
        @Schema(description =  "图片名")
        private String imgName;

        @ExcelProperty(value = "图片地址")
        @Schema(description =  "图片地址")
        private String imgUrl;

        @ExcelProperty(value = "顺序")
        @Schema(description =  "顺序")
        private Long imgSort;

        @ExcelProperty(value = "是否默认图")
        @Schema(description =  "是否默认图")
        private Long defaultImg;


}
