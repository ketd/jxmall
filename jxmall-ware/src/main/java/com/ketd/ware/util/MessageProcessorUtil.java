package com.ketd.ware.util;

import com.ketd.common.domain.mq.MultiDelayMessage;

import com.ketd.common.domain.mq.StockLockedTo;
import com.ketd.common.domain.mq.WareOrderTaskDetailTo;
import com.ketd.common.domain.order.OrderTO;
import com.ketd.common.enume.LockStatusEnum;
import com.ketd.common.enume.OrderStatusEnum;
import com.ketd.common.enume.RabbitMQConstants;
import com.ketd.common.no_authentication_api.order.NoAuthenticationOrderOpenFeignApi;
import com.ketd.ware.domain.WareOrderTask;
import com.ketd.ware.domain.WareOrderTaskDetail;
import com.ketd.ware.mapper.WareOrderTaskDetailMapper;
import com.ketd.ware.mapper.WareOrderTaskMapper;
import com.ketd.ware.mapper.WareSkuMapper;
import com.ketd.ware.service.impl.WareOrderTaskDetailServiceImpl;
import com.ketd.ware.service.impl.WareOrderTaskServiceImpl;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private WareSkuMapper wareSkuMapper;

    @Autowired
    private NoAuthenticationOrderOpenFeignApi noAuthenticationOrderOpenFeignApi;

    @Autowired
    private WareOrderTaskServiceImpl wareOrderTaskService;


    @Autowired
    private WareOrderTaskMapper  wareOrderTaskMapper;

    @Autowired
    private WareOrderTaskDetailMapper  wareOrderTaskDetailMapper;

    @Autowired
    private WareOrderTaskDetailServiceImpl wareOrderTaskDetailService;



    public void sendDelayedMessage(StockLockedTo data, List<Integer> delayTimes) {

        MultiDelayMessage<StockLockedTo> msg = new MultiDelayMessage<>();

        Integer nextDelay = delayTimes.remove(0);
        msg.setDelayMillis(new ArrayList<>(delayTimes));
        msg.setData(data);

        rabbitTemplate.convertAndSend(RabbitMQConstants.STOCK_RELEASE_WARE_EXCHANGE,
                RabbitMQConstants.STOCK_RELEASE_WARE_ROUTING_KEY, msg, message -> {
                    message.getMessageProperties().setDelay(nextDelay);
                    return message;
                });
    }



    /*
     * 描述:库存自动解锁
     * @description: 下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁。
     * @author: ketd
     * @date: 2024/6/2 17:24
     * @param null
     * @return: null
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMQConstants.STOCK_RELEASE_WARE_QUEUE, durable = "true"),
            exchange = @Exchange(name = RabbitMQConstants.STOCK_RELEASE_WARE_EXCHANGE, delayed = "true",type = ExchangeTypes.TOPIC),
            key = RabbitMQConstants.STOCK_RELEASE_WARE_ROUTING_KEY
    ))
    public void releaseOrderStock(MultiDelayMessage<StockLockedTo> message) {
        System.out.println("释放订单锁库存1：" + message);


        StockLockedTo stockLockedTo = message.getData();
        WareOrderTaskDetailTo wareOrderTaskDetailTo = stockLockedTo.getWareOrderTaskDetail();
        Long wareOrderTaskDetailId = wareOrderTaskDetailTo.getId();

        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(wareOrderTaskDetailId);
        if (wareOrderTaskDetail == null) {
            System.out.println("订单不存在");
            return;
        }else{


            Long wareOrderTaskId = stockLockedTo.getWareOrderTaskId();
            WareOrderTask wareOrderTask = wareOrderTaskService.getById(wareOrderTaskId);
            String orderSn = wareOrderTask.getOrderSn();
            OrderTO order  = noAuthenticationOrderOpenFeignApi.getInfoByOrderSn(orderSn).getData();

            if (order == null|| Objects.equals(order.getStatus(), OrderStatusEnum.CANCLED.getCode())) {

                if(Objects.equals(wareOrderTaskDetail.getLockStatus(), LockStatusEnum.LOCK.getCode())){
                    unLockStock(  wareOrderTaskDetail.getSkuId(), wareOrderTaskDetail.getWareId(), wareOrderTaskDetail.getSkuNum(), wareOrderTaskDetail.getId());
                    System.out.println("释放库存成功");
                    return;
                }

            }

        }


        List<Integer> delayMillis = message.getDelayMillis();

        if(message.hasNextDelay()) {
            sendDelayedMessage(stockLockedTo, delayMillis);
        }

    }


    private void unLockStock(Long skuId, Long wareId, Integer count,Long taskDetailId) {
        wareSkuMapper.unLockStock(skuId, wareId, count);
        //wareOrderTaskDetailService.unLockStock(taskDetailId);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMQConstants.STOCK_RELEASE_OTHER_QUEUE, durable = "true"),
            exchange = @Exchange(name = RabbitMQConstants.STOCK_RELEASE_OTHER_EXCHANGE, delayed = "true",type = ExchangeTypes.TOPIC),
            key = RabbitMQConstants.ORDER_RELEASE_OTHER_ROUTING_KEY
    ))
    public void releaseWareStock(OrderTO message) {
        System.out.println("释放订单锁库存2：" + message);
        unLockStock(message);

    }

    //防止订单卡顿，导致库存锁死不能自动解锁，主动解锁
    private void unLockStock(OrderTO message) {

        //查询最新的库存工作单状态
       WareOrderTask  wareOrderTask =  wareOrderTaskMapper.selectOneByOrderSn(message.getOrderSn());
       Long  wareOrderTaskId = wareOrderTask.getId();
        List<WareOrderTaskDetail>   wareOrderTaskDetails =  wareOrderTaskDetailMapper.selectAllByTaskIdAndLockStatus(wareOrderTaskId,LockStatusEnum.LOCK.getCode());
        for(WareOrderTaskDetail wareOrderTaskDetail: wareOrderTaskDetails){
            unLockStock(wareOrderTaskDetail.getSkuId(), wareOrderTaskDetail.getWareId(), wareOrderTaskDetail.getSkuNum(), wareOrderTaskDetail.getId());
        }
    }


}