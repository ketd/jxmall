package com.ketd.common.domain.coupon;


import lombok.Data;
import java.io.Serializable;


@Data
public class HomeSubjectSpuTO  implements Serializable
{


    private Long id;
    private String name;
    private Long subjectId;
    private Long spuId;
    private Long sort;


}
