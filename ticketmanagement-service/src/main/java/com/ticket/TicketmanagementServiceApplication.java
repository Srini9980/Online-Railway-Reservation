package com.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TicketmanagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketmanagementServiceApplication.class, args);
	}

}
