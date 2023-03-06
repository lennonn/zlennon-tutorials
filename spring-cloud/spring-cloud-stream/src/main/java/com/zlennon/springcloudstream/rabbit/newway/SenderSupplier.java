package com.zlennon.springcloudstream.rabbit.newway;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class SenderSupplier {

    @Bean
    public Supplier<String> sendModelId(){
        return ()->"modelId";
    }
}
