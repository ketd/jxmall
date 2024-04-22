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
 * 会员收藏的商品对象 ums_member_collect_spu
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_collect_spu")
@Data
public class MemberCollectSpu  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "会员id")
        @Schema(description =  "会员id")
        private Long memberId;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "spu_name")
        @Schema(description =  "spu_name")
        private String spuName;

        @ExcelProperty(value = "spu_img")
        @Schema(description =  "spu_img")
        private String spuImg;

        @ExcelProperty(value = "create_time")
        @Schema(description =  "create_time")
        private Date createTime;


}
