package com.zlennon.chatgptmodelservice.stream;

import com.zlennon.chatgptmodelservice.entity.Model;
import com.zlennon.chatgptmodelservice.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
@Slf4j
public class ModelIdConsumer {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    StreamBridge streamBridge;

    @Bean
    public Consumer<String> modelId() {
        return id->{
            log.info("getModelId:{}", id);
            //Model model = modelRepository.findById(id).get();
            Model model = new Model();
            model.setId(id);
            model.setObject("士大夫");
            streamBridge.send("model-in-0",model);
        };
    }

    @Bean
    public Supplier<Model> model(){
        return ()->{ //log.info("model Supplier ");
           return  new Model();
        } ;
    }
}
