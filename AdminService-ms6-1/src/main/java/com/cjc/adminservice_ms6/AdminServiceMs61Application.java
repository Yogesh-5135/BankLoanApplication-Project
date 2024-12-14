package com.cjc.adminservice_ms6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AdminServiceMs61Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceMs61Application.class, args);
	}

}
