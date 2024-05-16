package com.ketd.common.domain.member;


import java.math.BigDecimal;
import lombok.Data;
import java.io.Serializable;


@Data
public class MemberLevelTO  implements Serializable
{


    private Long id;
    private String name;
    private Long growthPoint;
    private Long defaultStatus;
    private BigDecimal freeFreightPoint;
    private Long commentGrowthPoint;
    private Long priviledgeFreeFreight;
    private Long priviledgeMemberPrice;
    private Long priviledgeBirthday;
    private String note;


}
