package com.ketd.member.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 会员等级对象 ums_member_level
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_level")
@Data
public class MemberLevel  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "等级名称")
        @Schema(description =  "等级名称")
        private String name;

        @ExcelProperty(value = "等级需要的成长值")
        @Schema(description =  "等级需要的成长值")
        private Long growthPoint;

        @ExcelProperty(value = "是否为默认等级[0-&gt;不是；1-&gt;是]")
        @Schema(description =  "是否为默认等级[0-&gt;不是；1-&gt;是]")
        private Long defaultStatus;

        @ExcelProperty(value = "免运费标准")
        @Schema(description =  "免运费标准")
        private BigDecimal freeFreightPoint;

        @ExcelProperty(value = "每次评价获取的成长值")
        @Schema(description =  "每次评价获取的成长值")
        private Long commentGrowthPoint;

        @ExcelProperty(value = "是否有免邮特权")
        @Schema(description =  "是否有免邮特权")
        private Long priviledgeFreeFreight;

        @ExcelProperty(value = "是否有会员价格特权")
        @Schema(description =  "是否有会员价格特权")
        private Long priviledgeMemberPrice;

        @ExcelProperty(value = "是否有生日特权")
        @Schema(description =  "是否有生日特权")
        private Long priviledgeBirthday;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;


}
