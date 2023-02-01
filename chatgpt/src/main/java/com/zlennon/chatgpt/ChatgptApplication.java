package com.zlennon.chatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChatgptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatgptApplication.class, args);
	}

}
