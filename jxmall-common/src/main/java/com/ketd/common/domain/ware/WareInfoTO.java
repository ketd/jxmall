package com.ketd.common.domain.ware;


import lombok.Data;
import java.io.Serializable;


@Data
public class WareInfoTO  implements Serializable
{


    private Long id;
    private String name;
    private String address;
    private String areacode;


}
