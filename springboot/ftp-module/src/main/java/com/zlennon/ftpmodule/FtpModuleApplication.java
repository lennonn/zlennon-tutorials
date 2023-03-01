package com.zlennon.ftpmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.zlennon")
@EnableJpaRepositories
@SpringBootApplication
public class FtpModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpModuleApplication.class, args);
	}

}
