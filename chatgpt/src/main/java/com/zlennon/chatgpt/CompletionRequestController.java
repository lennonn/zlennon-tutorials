package com.zlennon.chatgpt;

import com.alibaba.fastjson2.JSONObject;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.image.ImageResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatgpt")
public class CompletionRequestController {

    @Value("${token}")
    private String token;

    @GetMapping("/completion")
    public Object completionRequest(@RequestBody  String params) {
        CompletionRequest completionRequest = JSONObject.parseObject(params, CompletionRequest.class);
        OpenAiService service = new OpenAiService(token);
        System.out.println("\nCreating completion...");
/*        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("davinci-search-document")
                .prompt("地球将要毁灭")
                .echo(true)
                .user("testing")
                .build();*/
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        return ResponseEntity.ok(choices.toArray());
    }

    @GetMapping("/image")
    public Object imageRequest(@RequestBody  String params) {
        CreateImageRequest  createImageRequest = JSONObject.parseObject(params, CreateImageRequest.class);
        OpenAiService service = new OpenAiService(token);
        ImageResult imageResult = service.createImage(createImageRequest);
        return ResponseEntity.ok(imageResult);
    }
}
