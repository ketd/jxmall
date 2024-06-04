package com.ketd.common.enume;

public interface RabbitMQConstants {
    String STOCK_RELEASE_WARE_QUEUE = "stock-release-ware-queue";
    String STOCK_RELEASE_ORDER_QUEUE = "stock-release-order-queue";
    String STOCK_RELEASE_OTHER_QUEUE = "stock-release-other-queue";
    String STOCK_RELEASE_WARE_EXCHANGE = "stock-release-ware-exchange";
    String STOCK_RELEASE_ORDER_EXCHANGE = "stock-release-order-exchange";
    String STOCK_RELEASE_OTHER_EXCHANGE = "stock-release-other-exchange";
    String STOCK_RELEASE_WARE_ROUTING_KEY = "ware.release.key";
    String ORDER_RELEASE_ORDER_ROUTING_KEY = "order.release.key";
    String ORDER_RELEASE_OTHER_ROUTING_KEY = "other.release.key";

}
