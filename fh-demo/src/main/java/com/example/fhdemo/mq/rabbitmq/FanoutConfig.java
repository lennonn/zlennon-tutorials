package com.example.fhdemo.mq.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


//@Configuration
    public class FanoutConfig {

  @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new SerializerMessageConverter());
        return rabbitTemplate;
    }

    @Bean()
    public ConnectionFactory connectionFactory(RabbitProperties rabbitProperties){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(rabbitProperties.getHost());
        cachingConnectionFactory.setPort(rabbitProperties.getPort());
        cachingConnectionFactory.setUsername(rabbitProperties.getUsername());
        cachingConnectionFactory.setPassword(rabbitProperties.getPassword());
        cachingConnectionFactory.setVirtualHost("/");
        // 配置发送确认回调时，次配置必须配置，否则即使在RabbitTemplate配置了ConfirmCallback也不会生效
        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        return cachingConnectionFactory;
    }
   /*
        @Bean
        public FanoutExchange fanoutExchange() {
            return new FanoutExchange("startMix");
        }

        @Bean
        public Queue fanoutQueue1() {
            return new Queue("fanout_queue1");
        }
        @Bean
        public Binding binding1() {
            return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
        }*/
    @Bean
    public MessagePostProcessor correlationIdProcessor() {
        return new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message, Correlation correlation) {
                MessageProperties messageProperties = message.getMessageProperties();

                if (correlation instanceof CorrelationData) {
                    String correlationId = ((CorrelationData) correlation).getId();
                    messageProperties.setCorrelationId(correlationId);
                }
                // 可以设置持久化，但与本文无关，因此没有附上
                return message;
            }

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                return message;
            }
        };
    }
    }
