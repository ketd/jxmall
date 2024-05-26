package com.ketd.common.domain.member;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;


@Data
public class MemberTO  implements Serializable
{
    private Long id;
    private Long levelId;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String header;
    private Long gender;
    private Date birth;
    private String city;
    private String job;
    private String sign;
    private Long sourceType;
    private Long integration;
    private Long growth;
    private Long status;
    private Date createTime;


}
