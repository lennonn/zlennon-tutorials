package com.zlennon.chatgptmodelservice.controller;

import com.zlennon.chatgptmodelservice.repository.ModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class ModelController {

    @Autowired
    ModelRepository modelRepository;


    @RequestMapping("/test")
    public void test(HttpServletRequest request){
        log.info("get traceId:{}, spanId:{}");

    }
}
