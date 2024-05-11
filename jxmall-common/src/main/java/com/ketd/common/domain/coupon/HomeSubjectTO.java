package com.ketd.common.domain.coupon;


import lombok.Data;
import java.io.Serializable;


@Data
public class HomeSubjectTO  implements Serializable
{


    private Long id;
    private String name;
    private String title;
    private String subTitle;
    private Integer status;
    private String url;
    private Long sort;
    private String img;


}
