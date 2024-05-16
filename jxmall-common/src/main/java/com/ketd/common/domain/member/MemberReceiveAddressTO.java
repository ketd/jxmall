package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;


@Data
public class MemberReceiveAddressTO  implements Serializable
{


    private Long id;
    private Long memberId;
    private String name;
    private String phone;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;
    private String areacode;
    private Integer defaultStatus;


}
