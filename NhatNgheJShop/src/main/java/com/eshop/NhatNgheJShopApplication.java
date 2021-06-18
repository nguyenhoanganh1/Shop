package com.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.eshop.entity")
public class NhatNgheJShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhatNgheJShopApplication.class, args);
	}

}
