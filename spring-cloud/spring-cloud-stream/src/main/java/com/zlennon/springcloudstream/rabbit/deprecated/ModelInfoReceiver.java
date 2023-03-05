package com.zlennon.springcloudstream.rabbit.deprecated;

import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.messaging.Message;


@EnableBinding(value ={MySink.class})
public class ModelInfoReceiver {

    private static Logger logger = LoggerFactory.getLogger(ModelInfoReceiver.class);

    @StreamListener(MySink.INPUT)
    public void receive(Message<String> payload) {
        logger.info("Received: " +payload.getPayload());
    }
}
