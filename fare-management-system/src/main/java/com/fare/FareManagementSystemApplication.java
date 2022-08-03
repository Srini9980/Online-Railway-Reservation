package com.fare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FareManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FareManagementSystemApplication.class, args);
	}

}
