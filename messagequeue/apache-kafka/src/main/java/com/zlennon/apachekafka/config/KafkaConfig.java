package com.zlennon.apachekafka.config;

import com.zlennon.apachekafka.service.MessageListener;
import com.zlennon.apachekafka.service.MessageProducer;
import com.zlennon.apachekafka.service.MultiMessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaConfig {

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    @Bean
    public MultiMessageProducer multiMessageProducer() {
        return new MultiMessageProducer();
    }

}
