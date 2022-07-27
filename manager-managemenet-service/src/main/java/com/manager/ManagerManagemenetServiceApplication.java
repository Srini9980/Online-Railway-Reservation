package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ManagerManagemenetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerManagemenetServiceApplication.class, args);
	}

}
