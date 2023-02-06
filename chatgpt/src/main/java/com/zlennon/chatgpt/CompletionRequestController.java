package com.zlennon.chatgpt;

import com.alibaba.fastjson2.JSONObject;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.image.ImageResult;
import com.zlennon.chatgpt.model.ReqResItemsEntity;
import com.zlennon.chatgpt.repository.ReqResItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatgpt")
public class CompletionRequestController {

    @Value("${token}")
    private String token;

    @Autowired
    private ReqResItemsRepository reqResItemsRepository;

    @GetMapping("/completion")
    public Object completionRequest(@RequestBody  String params) {
        CompletionRequest completionRequest = JSONObject.parseObject(params, CompletionRequest.class);
        OpenAiService service = new OpenAiService(token);
        System.out.println("\nCreating completion...");
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        ReqResItemsEntity resItemsEntity = new ReqResItemsEntity();
        resItemsEntity.setApi("/v1/completions");
        resItemsEntity.setReqInfo(JSONObject.parseObject(params).toString());
        resItemsEntity.setResInfo(JSONObject.toJSONString(choices));
        resItemsEntity.setType(1);
        reqResItemsRepository.save(resItemsEntity);
        return ResponseEntity.ok(choices.toArray());
    }

    @GetMapping("/image")
    public Object imageRequest(@RequestBody  String params) {
        CreateImageRequest  createImageRequest = JSONObject.parseObject(params, CreateImageRequest.class);
        OpenAiService service = new OpenAiService(token);
        ImageResult imageResult = service.createImage(createImageRequest);
        ReqResItemsEntity resItemsEntity = new ReqResItemsEntity();
        resItemsEntity.setApi("/v1/images/generations");
        resItemsEntity.setReqInfo(JSONObject.parseObject(params).toString());
        resItemsEntity.setResInfo(JSONObject.toJSONString(imageResult));
        resItemsEntity.setType(2);
        reqResItemsRepository.save(resItemsEntity);
        return ResponseEntity.ok(imageResult);
    }
}