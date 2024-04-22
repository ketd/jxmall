package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@Data
public class SpuCommentTO  implements Serializable
{


    private Long id;
    private Long skuId;
    private Long spuId;
    private String spuName;
    private String memberNickName;
    private Integer star;
    private String memberIp;
    private Date createTime;
    private Integer showStatus;
    private String spuAttributes;
    private Long likesCount;
    private Long replyCount;
    private String resources;
    private String content;
    private String memberIcon;
    private Long commentType;


}
