package com.zlennon.apachekafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiListenerService {

    @Autowired
    MultiMessageProducer producer;

    public void execute() throws InterruptedException {

        producer.sendMessages();

        Thread.sleep(5000);
    }

}
