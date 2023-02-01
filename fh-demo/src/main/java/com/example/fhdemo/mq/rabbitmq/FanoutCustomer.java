package com.example.fhdemo.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.fhdemo.logdesensitization.CustomPatternLayout.logger;

//@Component
@Slf4j
public class FanoutCustomer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(),
            exchange = @Exchange(name = "startMix", type = ExchangeTypes.FANOUT)
    ))
    public void start(String data, Channel channel, Message message)  throws IOException {
        logger.info("mq consume startMix:{}"+ data);
    }
}
