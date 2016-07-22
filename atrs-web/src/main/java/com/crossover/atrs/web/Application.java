package com.crossover.atrs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class responsible to start the application with spring-boot starter
 * 
 * @author valverde.thiago
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(WebConfiguration.class, args).getEnvironment().setActiveProfiles("test");
	}

}
