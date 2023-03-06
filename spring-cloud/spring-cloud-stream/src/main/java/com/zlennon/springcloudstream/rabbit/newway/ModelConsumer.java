package com.zlennon.springcloudstream.rabbit.newway;

import com.zlennon.commonentity.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class ModelConsumer {

    @Bean
    public Consumer<Model> model() {
        return model->{
            log.info("query by modelid is:{}", model);
        };
    }
}
