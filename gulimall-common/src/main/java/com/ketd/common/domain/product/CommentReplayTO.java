package com.ketd.common.domain.product;


import lombok.Data;
import java.io.Serializable;


@Data
public class CommentReplayTO  implements Serializable
{


    private Long id;
    private Long commentId;
    private Long replyId;


}
