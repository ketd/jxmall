package com.ketd.common.domain.member;


import lombok.Data;
import java.io.Serializable;


@Data
public class MemberCollectSubjectTO  implements Serializable
{


    private Long id;
    private Long subjectId;
    private String subjectName;
    private String subjectImg;
    private String subjectUrll;


}
