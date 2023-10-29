package com.lizana.microservicecredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@AutoConfiguration
public class MicroserviceCreditApplication {

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCreditApplication.class, args);
	}


}
