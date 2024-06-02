package com.ketd.common.domain.mq;


import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class  MultiDelayMessage<T> implements Serializable {
    /**
     * 消息体
     */
    private T data;
    /**
     * 记录延迟时间的集合
     */
    private List<Integer> delayMillis;

//    public MultiDelayMessage(Long data, List<Integer> delayMillis) {
//        this.data = data;
//        this.delayMillis = delayMillis;
//    }
//    public static <Long> MultiDelayMessage<Long> of(Long data, Integer ... delayMillis){
//        return new MultiDelayMessage<>(data, new ArrayList<>(List.of(delayMillis)));
//    }


    /**
     * 获取并移除下一个延迟时间
     * @return 队列中的第一个延迟时间
     */
    public Integer removeNextDelay(){
        return delayMillis.remove(0);
    }

    /**
     * 是否还有下一个延迟时间
     */
    public boolean hasNextDelay(){
        return !delayMillis.isEmpty();
    }
}
