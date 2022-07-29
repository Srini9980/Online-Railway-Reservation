package com.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdminManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminManagementServiceApplication.class, args);
	}

}
