package com.cjc.cibil.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CibilMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(CibilMs2Application.class, args);
	}

}
