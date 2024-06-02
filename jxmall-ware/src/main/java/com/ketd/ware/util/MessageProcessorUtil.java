package com.ketd.ware.util;

import com.ketd.common.domain.mq.MultiDelayMessage;

import com.ketd.common.domain.mq.StockLockedTo;
import com.ketd.ware.domain.WareOrderTaskDetail;
import com.ketd.ware.enume.RabbitMQConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.common.utils
 * @Author: ketd
 * @CreateTime: 2024-06-01  13:49
 */
@Service
public class MessageProcessorUtil {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void sendDelayedMessage(StockLockedTo data, List<Integer> delayTimes) {

        MultiDelayMessage<StockLockedTo> msg = new MultiDelayMessage<>();

        Integer nextDelay = delayTimes.remove(0);
        msg.setDelayMillis(new ArrayList<>(delayTimes));
        msg.setData(data);

        rabbitTemplate.convertAndSend(RabbitMQConstants.STOCK_RELEASE_ORDER_EXCHANGE,
                RabbitMQConstants.STOCK_RELEASE_ORDER_ROUTING_KEY, msg, message -> {
                    message.getMessageProperties().setDelay(nextDelay);
                    return message;
                });
    }


}