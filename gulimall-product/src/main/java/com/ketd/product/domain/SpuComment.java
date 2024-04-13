package com.ketd.product.domain;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商品评价对象 pms_spu_comment
 *
 * @author ketd
 * @date 2024-04-12
 */

@TableName(value ="pms_spu_comment")
@Data
public class SpuComment  implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;



        @TableId
        @ExcelIgnore
        @ExcelProperty(value = "id")
        @Schema(description =  "id")
        private Long id;

        @ExcelProperty(value = "sku_id")
        @Schema(description =  "sku_id")
        private Long skuId;

        @ExcelProperty(value = "spu_id")
        @Schema(description =  "spu_id")
        private Long spuId;

        @ExcelProperty(value = "商品名字")
        @Schema(description =  "商品名字")
        private String spuName;

        @ExcelProperty(value = "会员昵称")
        @Schema(description =  "会员昵称")
        private String memberNickName;

        @ExcelProperty(value = "星级")
        @Schema(description =  "星级")
        private Integer star;

        @ExcelProperty(value = "会员ip")
        @Schema(description =  "会员ip")
        private String memberIp;

        @ExcelProperty(value = "创建时间")
        @Schema(description =  "创建时间")
        private Date createTime;

        @ExcelProperty(value = "显示状态[0-不显示，1-显示]")
        @Schema(description =  "显示状态[0-不显示，1-显示]")
        private Integer showStatus;

        @ExcelProperty(value = "购买时属性组合")
        @Schema(description =  "购买时属性组合")
        private String spuAttributes;

        @ExcelProperty(value = "点赞数")
        @Schema(description =  "点赞数")
        private Long likesCount;

        @ExcelProperty(value = "回复数")
        @Schema(description =  "回复数")
        private Long replyCount;

        @ExcelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
        @Schema(description =  "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
        private String resources;

        @ExcelProperty(value = "内容")
        @Schema(description =  "内容")
        private String content;

        @ExcelProperty(value = "用户头像")
        @Schema(description =  "用户头像")
        private String memberIcon;

        @ExcelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
        @Schema(description =  "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
        private Long commentType;


}
