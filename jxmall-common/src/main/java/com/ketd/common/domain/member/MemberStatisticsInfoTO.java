package com.ketd.common.domain.member;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class MemberStatisticsInfoTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private BigDecimal consumeAmount;
    private BigDecimal couponAmount;
    private Long orderCount;
    private Long couponCount;
    private Long commentCount;
    private Long returnOrderCount;
    private Long loginCount;
    private Long attendCount;
    private Long fansCount;
    private Long collectProductCount;
    private Long collectSubjectCount;
    private Long collectCommentCount;
    private Long inviteFriendCount;


}
