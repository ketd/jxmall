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
 * 会员统计信息对象 ums_member_statistics_info
 *
 * @author ketd
 * @date 2024-04-18
 */

@TableName(value ="ums_member_statistics_info")
@Data
public class MemberStatisticsInfo  implements Serializable
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

        @ExcelProperty(value = "累计消费金额")
        @Schema(description =  "累计消费金额")
        private BigDecimal consumeAmount;

        @ExcelProperty(value = "累计优惠金额")
        @Schema(description =  "累计优惠金额")
        private BigDecimal couponAmount;

        @ExcelProperty(value = "订单数量")
        @Schema(description =  "订单数量")
        private Long orderCount;

        @ExcelProperty(value = "优惠券数量")
        @Schema(description =  "优惠券数量")
        private Long couponCount;

        @ExcelProperty(value = "评价数")
        @Schema(description =  "评价数")
        private Long commentCount;

        @ExcelProperty(value = "退货数量")
        @Schema(description =  "退货数量")
        private Long returnOrderCount;

        @ExcelProperty(value = "登录次数")
        @Schema(description =  "登录次数")
        private Long loginCount;

        @ExcelProperty(value = "关注数量")
        @Schema(description =  "关注数量")
        private Long attendCount;

        @ExcelProperty(value = "粉丝数量")
        @Schema(description =  "粉丝数量")
        private Long fansCount;

        @ExcelProperty(value = "收藏的商品数量")
        @Schema(description =  "收藏的商品数量")
        private Long collectProductCount;

        @ExcelProperty(value = "收藏的专题活动数量")
        @Schema(description =  "收藏的专题活动数量")
        private Long collectSubjectCount;

        @ExcelProperty(value = "收藏的评论数量")
        @Schema(description =  "收藏的评论数量")
        private Long collectCommentCount;

        @ExcelProperty(value = "邀请的朋友数量")
        @Schema(description =  "邀请的朋友数量")
        private Long inviteFriendCount;


}
