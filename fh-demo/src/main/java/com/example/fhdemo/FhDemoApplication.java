package com.example.fhdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.ReactiveRedisOperations;

@Slf4j
@SpringBootApplication
public class FhDemoApplication{

	//@Autowired
	static RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(FhDemoApplication.class, args);
		System.out.println("sdfsdfdsfdsf");
	}



}
