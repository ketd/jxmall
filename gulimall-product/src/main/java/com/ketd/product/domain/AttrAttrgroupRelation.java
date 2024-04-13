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
 * 属性&属性分组关联对象 pms_attr_attrgroup_relation
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_attr_attrgroup_relation")
@Data
public class AttrAttrgroupRelation  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "属性id")
        @Schema(description =  "属性id")
        private Long attrId;

        @ExcelProperty(value = "属性分组id")
        @Schema(description =  "属性分组id")
        private Long attrGroupId;

        @ExcelProperty(value = "属性组内排序")
        @Schema(description =  "属性组内排序")
        private Long attrSort;


}
