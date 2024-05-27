package com.ketd.common.domain.order;


import lombok.Data;
import java.io.Serializable;


@Data
public class OrderSettingTO  implements Serializable
{


    private Long id;
    private Long flashOrderOvertime;
    private Long normalOrderOvertime;
    private Long confirmOvertime;
    private Long finishOvertime;
    private Long commentOvertime;
    private Long memberLevel;


}
