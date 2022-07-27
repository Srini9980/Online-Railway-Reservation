package com.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrainManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainManagementServiceApplication.class, args);
	}

}
