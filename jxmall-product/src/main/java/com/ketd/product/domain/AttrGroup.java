package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 属性分组对象 pms_attr_group
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_attr_group")
@Data
public class AttrGroup  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "分组id")
        @Schema(description =  "分组id")
        private Long attrGroupId;

        @ExcelProperty(value = "组名")
        @Schema(description =  "组名")
        private String attrGroupName;

        @ExcelProperty(value = "排序")
        @Schema(description =  "排序")
        private Long sort;

        @ExcelProperty(value = "描述")
        @Schema(description =  "描述")
        private String descript;

        @ExcelProperty(value = "组图标")
        @Schema(description =  "组图标")
        private String icon;

        @ExcelProperty(value = "所属分类id")
        @Schema(description =  "所属分类id")
        private Long catelogId;

        @TableField(exist = false)
        private Long[] catelogPath;


}
