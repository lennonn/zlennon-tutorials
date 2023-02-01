package com.example.fhdemo;

import com.example.fhdemo.FhDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@Slf4j
public class SendAndReceivedMsgTest {

    @Autowired
    static RabbitTemplate rabbitTemplate;

    @Value("${imAsynSend:true}")
    private boolean imAsynSend;

    @Test
    public void testSendAndReceivedMsg() throws Exception {

        System.out.println(imAsynSend+"==="+imAsynSend);
        final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
            String messageId = correlationData.getId();
            if (ack) {
                log.info("Mq消息发送成功，correlationData：{} ， messageId：{},cause:{}", correlationData, messageId,cause);
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                log.error("Mq消息发送异常，correlationData：{} ， messageId：{},case:{}", correlationData, messageId,cause);
            }
        };
        ConfigurableApplicationContext run = SpringApplication.run(FhDemoApplication.class);
        rabbitTemplate=run.getBean(RabbitTemplate.class);
        MessagePostProcessor messagePostProcessor =run.getBean(MessagePostProcessor.class);
        CorrelationData correlationData = new CorrelationData("sssss-"+System.currentTimeMillis());
        String msg="{\"timestamp\":1665763173,\"eventId\":1899121568,\"para\":{\"mix_stream_session_id\":\"20220315FmgCd5VsiIG0L5xb\",\"output_stream_type\":\"1\",\"output_stream_id\":\"20220315FmgCd5VsiIG0L5xb\",\"is_update_layer\":true,\"roomid\":\"30786\",\"sessionId\":\"1400335539_30786\",\"serialNumber\":\"T2020000120220315000000084\",\"userid\":\"30786_u1\",\"customid\":\"88\",\"input_stream_list\":[{\"input_stream_id\":\"canvas1\",\"layout_params\":{\"image_layer\":1,\"input_type\":3,\"image_width\":1920,\"image_height\":1080}},{\"input_stream_id\":\"1400335539_30786_30786_u1_main\",\"layout_params\":{\"image_layer\":2,\"image_width\":0.500000000000000,\"image_height\":0.500000000000000,\"location_x\":0.000000000000000,\"location_y\":0.000000000000000}},{\"input_stream_id\":\"1400335539_30786_30786_u2_main\",\"layout_params\":{\"image_layer\":3,\"image_width\":0.500000000000000,\"image_height\":0.500000000000000,\"location_x\":0.500000000000000,\"location_y\":0.000000000000000}}]}}";
        rabbitTemplate.convertAndSend("startMix", "", msg,messagePostProcessor,correlationData);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        Message message = rabbitTemplate.receive("startMix");
        Object receiveAndConvert = rabbitTemplate.receiveAndConvert("startMix");
        log.info("Received:{},message:{}",receiveAndConvert,message);
        Object o = rabbitTemplate.receiveAndConvert("startMix");
        System.out.println("消息发送成功==="+o);
    }
}
