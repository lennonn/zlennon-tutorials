package com.zlennon.apachekafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class MultiMessageProducer {
    @Autowired
    private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    @Value(value = "${multi.type.topic.name}")
    private String multiTypeTopicName;

    public void sendMessages() {
        multiTypeKafkaTemplate.send(multiTypeTopicName, new Greeting("Greetings", "World!"));
        multiTypeKafkaTemplate.send(multiTypeTopicName, new Farewell("Farewell", 25));
        multiTypeKafkaTemplate.send(multiTypeTopicName, "Simple string message");
    }
}
