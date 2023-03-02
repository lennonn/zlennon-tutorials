package com.zlennon.chatgptapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChatgptApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatgptApiServiceApplication.class, args);
	}

}
