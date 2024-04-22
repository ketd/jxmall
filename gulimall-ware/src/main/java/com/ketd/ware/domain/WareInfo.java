package com.ketd.ware.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 仓库信息对象 wms_ware_info
 *
 * @author ketd
 * @date 2024-04-21
 */

@TableName(value ="wms_ware_info")
@Data
public class WareInfo  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "仓库名")
        @Schema(description =  "仓库名")
        private String name;

        @ExcelProperty(value = "仓库地址")
        @Schema(description =  "仓库地址")
        private String address;

        @ExcelProperty(value = "区域编码")
        @Schema(description =  "区域编码")
        private String areacode;


}
