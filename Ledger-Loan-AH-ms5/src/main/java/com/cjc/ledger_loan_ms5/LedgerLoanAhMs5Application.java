package com.cjc.ledger_loan_ms5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class LedgerLoanAhMs5Application {

	public static void main(String[] args) {
		SpringApplication.run(LedgerLoanAhMs5Application.class, args);
	}

	@Bean
	public RestTemplate rt() {
		return new RestTemplate();
	}
}
