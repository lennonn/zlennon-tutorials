package com.zlennon.openfeign.client;


import com.zlennon.chatgpt.ReqResItemsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="chatgpt-service")
//,url = "http://localhost:9999/chatgpt"
@Component
public interface ChatGPTClient {

    @RequestMapping(value = "/getChatGPTById/{chatId}")
    ReqResItemsEntity getChatGPTById(@PathVariable("chatId") Long chatId);
}
