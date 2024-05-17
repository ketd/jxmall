package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;


@Data
public class MemberSocialTO  implements Serializable
{


    private Long memberId;
    private String socialUid;
    private String accessToken;
    private Long exporesIn;
    private String avatarUrl;
    private String name;
    private Integer type;


}
