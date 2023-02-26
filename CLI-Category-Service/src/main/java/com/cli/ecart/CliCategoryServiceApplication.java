package com.cli.ecart;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan({ "com.cli.ecart.entity" })
public class CliCategoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CliCategoryServiceApplication.class);
		/*
		 * below property disable the startup spring symbol
		 */
		// springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

}
