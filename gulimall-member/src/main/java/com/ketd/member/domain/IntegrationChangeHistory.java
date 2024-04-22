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
 * 积分变化历史记录对象 ums_integration_change_history
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_integration_change_history")
@Data
public class IntegrationChangeHistory  implements Serializable
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

        @ExcelProperty(value = "变化的值")
        @Schema(description =  "变化的值")
        private Long changeCount;

        @ExcelProperty(value = "备注")
        @Schema(description =  "备注")
        private String note;

        @ExcelProperty(value = "来源[0-&gt;购物；1-&gt;管理员修改;2-&gt;活动]")
        @Schema(description =  "来源[0-&gt;购物；1-&gt;管理员修改;2-&gt;活动]")
        private Long sourceTyoe;


}
