package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class MemberCollectSpuTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private Long spuId;
    private String spuName;
    private String spuImg;
    private Date createTime;


}
