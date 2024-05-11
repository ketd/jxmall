package com.ketd.member.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 成长值变化历史记录对象 ums_growth_change_history
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_growth_change_history")
@Data
public class GrowthChangeHistory  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "member_id")
        @Schema(description =  "member_id")
        private Long memberId;

        @ExcelProperty(value = "create_time")
        @Schema(description =  "create_time")
        private Date createTime;

        @ExcelProperty(value = "改变的值（正负计数）")
        @Schema(description =  "改变的值（正负计数）")
        private Long changeCount;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;

        @ExcelProperty(value = "积分来源[0-购物，1-管理员修改]")
        @Schema(description =  "积分来源[0-购物，1-管理员修改]")
        private Long sourceType;


}
