package com.poc.mglApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class MglApplication {

	public static void main(String[] args) {
		SpringApplication.run(MglApplication.class, args);
	}

}
