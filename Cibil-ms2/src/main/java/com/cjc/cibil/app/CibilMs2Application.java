package com.cjc.cibil.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
public class CibilMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(CibilMs2Application.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate rt()
	{
		RestTemplate rt = new RestTemplate();
		
		return rt;
	}
}
