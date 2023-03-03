package com.zlennon.chatgptmodelservice.controller;

import com.zlennon.chatgptmodelservice.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    @Autowired
    ModelRepository modelRepository;


}
