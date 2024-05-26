import com.ketd.order.to.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: PACKAGE_NAME
 * @Author: ketd
 * @CreateTime: 2024-05-20  16:08
 */
@SpringBootTest(classes = com.ketd.order.Order.class)
public class RabbitTest {
    @Autowired
    private RabbitTemplate  rabbitTemplate;

    @Test
    public void test(){
        rabbitTemplate.convertAndSend("simple","hello");
    }

    @Test
    public void testDirect(){
        rabbitTemplate.convertAndSend("jxmall.direct","yellow","hello");
    }



    @RabbitListener(queues = "direct.queue1")
    public void test2(User msg){
        System.out.println(msg);
    }

/*    @RabbitListener(queues = "direct.queue1")
    public void queue1(String msg){
        System.out.println("queue1:"+msg);
    }
    @RabbitListener(queues = "direct.queue2")
    public void queue2(String msg){
        System.out.println("queue2:"+msg);
    }*/

    @Test
    public void testPersistent(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","ketd");
        map.put("age",18);
        Message message = MessageBuilder
                .withBody("你好".getBytes(StandardCharsets.UTF_8)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();

       for (int i = 0; i < 10000; i++) {
           rabbitTemplate.convertAndSend("direct.queue2",message);
       }
    }

    @Test
    public void testJson(){
        User user = new User();
        user.setName("ketd");
        user.setPassword("123");
        rabbitTemplate.convertAndSend("jxmall.direct","blue",user);
    }


}
