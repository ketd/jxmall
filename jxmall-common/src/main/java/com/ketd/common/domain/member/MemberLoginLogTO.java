package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class MemberLoginLogTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private Date createTime;
    private String ip;
    private String city;
    private Integer loginType;


}
