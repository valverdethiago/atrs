package com.crossover.atrs.web;

import org.springframework.boot.SpringApplication;

/**
 * Class responsible to start the application with spring-boot starter
 * 
 * @author valverde.thiago
 *
 */
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args).getEnvironment().setActiveProfiles("test");
	}

}
