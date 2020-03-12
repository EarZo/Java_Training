package com.cellterion.smartphoneservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.cellterion.smartphoneservice.model")
public class SmartphoneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartphoneServiceApplication.class, args);
	}

}
