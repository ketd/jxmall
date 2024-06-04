package com.ketd.order.service;

import com.ketd.order.domain.Order;

import java.util.List;

public interface RabbitMQService {

    public  void sendReleaseOrderDelayedMessage(Order data, List<Integer> delayTimes);
}
