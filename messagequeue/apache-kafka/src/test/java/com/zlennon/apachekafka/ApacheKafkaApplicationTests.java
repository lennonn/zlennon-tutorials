package com.zlennon.apachekafka;

import com.zlennon.apachekafka.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApacheKafkaApplicationTests {

    @Autowired
    MessageService messageService;

    @Test
    void testMessageService(){
        assertNotNull(messageService);
        messageService.execute();
    }

    @Test
    void testMultiTypeMessage()  {
        assertNotNull(messageService);
        messageService.multiTypeExecute();
    }
}
