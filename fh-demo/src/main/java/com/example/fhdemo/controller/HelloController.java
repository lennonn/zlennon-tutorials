package com.example.fhdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @Value("${server.instance.id}")
    String instanceId;

    @PostMapping("/hello")
    public String hello(HttpServletRequest request)
    {
        return String.format("Hello from instance %s", instanceId);
    }
}
