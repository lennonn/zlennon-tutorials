package com.zlennon.apachekafka;

import com.zlennon.apachekafka.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApacheKafkaApplicationTests {

    @Autowired
    MessageService messageService;

    @Test
    void testMessageServcie() throws InterruptedException {
        messageService.execute();
    }

}
