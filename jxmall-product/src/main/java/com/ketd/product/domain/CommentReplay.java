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
 * 商品评价回复关系对象 pms_comment_replay
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_comment_replay")
@Data
public class CommentReplay  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "评论id")
        @Schema(description =  "评论id")
        private Long commentId;

        @ExcelProperty(value = "回复id")
        @Schema(description =  "回复id")
        private Long replyId;


}
