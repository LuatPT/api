package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.api.controller.TrainerController;

@SpringBootApplication
@ComponentScan(basePackageClasses = TrainerController.class, basePackages = "com.api")
public class TpbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpbsApplication.class, args);
	}

}
