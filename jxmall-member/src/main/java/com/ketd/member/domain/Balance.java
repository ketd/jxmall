package com.ketd.member.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户余额表对象 ums_balance
 *
 * @author ketd
 * @date 2024-06-04
 */

@TableName(value ="ums_balance")
@Data
public class Balance  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "${column.columnComment}")
        @Schema(description =  "${column.columnComment}")
        private Long id;

        @ExcelProperty(value = "用户id")
        @Schema(description =  "用户id")
        private Long memberId;

        @ExcelProperty(value = "余额")
        @Schema(description =  "余额")
        private BigDecimal balance;

        @ExcelProperty(value = "更新时间")
        @Schema(description =  "更新时间")
        private Date updatedAt;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createdAt;

        @ExcelProperty(value = "1-正常(normal),2-冻结(frozen),3-冻结(frozen)")
        @Schema(description =  "1-正常(normal),2-冻结(frozen),3-冻结(frozen)")
        private Integer status;


}
