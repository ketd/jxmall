
import com.ketd.ware.enume.RabbitMQConstants;
import org.junit.jupiter.api.Test;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: PACKAGE_NAME
 * @Author: ketd
 * @CreateTime: 2024-06-01  12:25
 */
@SpringBootTest(classes = com.ketd.ware.Ware.class)
public class WareTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = RabbitMQConstants.STOCK_RELEASE_ORDER_QUEUE, durable = "true"),
            exchange = @Exchange(name = RabbitMQConstants.STOCK_RELEASE_ORDER_EXCHANGE, delayed = "true"),
            key = RabbitMQConstants.STOCK_RELEASE_ORDER_ROUTING_KEY
    ))
    public void releaseOrderStock(String orderToken) {
        System.out.println("释放订单锁库存");
        // wareSkuMapper.unLockStock(orderToken);
    }



    @Test
    public void testSendMessage() {
        rabbitTemplate.convertAndSend("stock-release-order-exchange", "stock.release.order", "helloWorld", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(10000);
                return message;
            }
        });
    }
}
