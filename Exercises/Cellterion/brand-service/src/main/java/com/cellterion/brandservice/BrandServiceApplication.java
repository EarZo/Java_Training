package com.cellterion.brandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.cellterion.brandservice.model")
@EnableCircuitBreaker
public class BrandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrandServiceApplication.class, args);
	}

}
