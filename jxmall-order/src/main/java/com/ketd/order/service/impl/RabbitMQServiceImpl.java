package com.ketd.order.service.impl;

import com.ketd.common.domain.mq.MultiDelayMessage;
import com.ketd.common.domain.order.OrderTO;
import com.ketd.common.enume.OrderStatusEnum;
import com.ketd.common.enume.RabbitMQConstants;
import com.ketd.order.domain.Order;
import com.ketd.order.mapper.OrderMapper;
import com.ketd.order.service.RabbitMQService;
import com.ketd.order.util.MessageProcessorUtil;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.order.service.impl
 * @Author: ketd
 * @CreateTime: 2024-06-02  19:51
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MessageProcessorUtil messageProcessorUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void sendReleaseOrderDelayedMessage(Order data, List<Integer> delayTimes)   {

        MultiDelayMessage<Order> msg = new MultiDelayMessage<>();

        Integer nextDelay = delayTimes.remove(0);
        msg.setDelayMillis(new ArrayList<>(delayTimes));
        msg.setData(data);

        rabbitTemplate.convertAndSend(RabbitMQConstants.STOCK_RELEASE_ORDER_EXCHANGE,
                RabbitMQConstants.ORDER_RELEASE_ORDER_ROUTING_KEY, msg, message -> {
                    message.getMessageProperties().setDelay(nextDelay);
                    return message;
                });

    }

    public  void sendReleaseWareDelayedMessage(OrderTO data)   {

        rabbitTemplate.convertAndSend(RabbitMQConstants.STOCK_RELEASE_OTHER_EXCHANGE,
                RabbitMQConstants.ORDER_RELEASE_OTHER_ROUTING_KEY, data, message -> {
                    message.getMessageProperties().setDelay(1000);
                    return message;
                });
    }



    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMQConstants.STOCK_RELEASE_ORDER_QUEUE, durable = "true"),
            exchange = @Exchange(name = RabbitMQConstants.STOCK_RELEASE_ORDER_EXCHANGE, delayed = "true",type = ExchangeTypes.TOPIC),
            key = RabbitMQConstants.ORDER_RELEASE_ORDER_ROUTING_KEY
    ))
    public void releaseOrderStock(MultiDelayMessage<Order> message) {


        System.out.println("释放订单库存消息"+message);

        Order order = message.getData();
        List<Integer> delayMillis = message.getDelayMillis();

        // 判断订单是否已经支付
       Order newOrder = orderMapper.selectById(order.getId());
       if(!message.hasNextDelay()&&Objects.equals(newOrder.getStatus(), OrderStatusEnum.CREATE_NEW.getCode())){

           newOrder.setStatus(OrderStatusEnum.CANCLED.getCode());
           orderMapper.updateById(newOrder);

           OrderTO orderTO = new OrderTO();
           BeanUtils.copyProperties(newOrder,orderTO);

           System.out.println("向库存发送消息"+orderTO);
           sendReleaseWareDelayedMessage(orderTO);
           return;
       }


        if(message.hasNextDelay()) {
            sendReleaseOrderDelayedMessage(newOrder, delayMillis);
        }

    }
}
