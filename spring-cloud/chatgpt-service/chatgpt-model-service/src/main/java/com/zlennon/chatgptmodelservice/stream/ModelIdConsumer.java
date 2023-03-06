package com.zlennon.chatgptmodelservice.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class ModelIdConsumer {

    @Bean
    public Consumer<String> getModelId() {
        return str->log.info("getModelId:{}", str);
    }
}
