package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class GrowthChangeHistoryTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private Date createTime;
    private Long changeCount;
    private String note;
    private Long sourceType;


}
