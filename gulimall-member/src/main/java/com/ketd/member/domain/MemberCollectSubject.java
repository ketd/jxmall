package com.ketd.member.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 会员收藏的专题活动对象 ums_member_collect_subject
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_collect_subject")
@Data
public class MemberCollectSubject  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "subject_id")
        @Schema(description =  "subject_id")
        private Long subjectId;

        @ExcelProperty(value = "subject_name")
        @Schema(description =  "subject_name")
        private String subjectName;

        @ExcelProperty(value = "subject_img")
        @Schema(description =  "subject_img")
        private String subjectImg;

        @ExcelProperty(value = "活动url")
        @Schema(description =  "活动url")
        private String subjectUrll;


}
