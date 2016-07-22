package com.crossover.atrs.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.crossover.atrs.respository.DatabaseConfiguration;
import com.crossover.atrs.service.ServiceConfiguration;
import com.crossover.atrs.web.security.AuthorizationServerConfiguration;
import com.crossover.atrs.web.security.ResourceServerConfiguration;
import com.crossover.atrs.web.security.SecurityConfiguration;

/**
 * Configuration class that holds the web configuration (controller layer) of
 * the application
 * 
 * @author valverde.thiago
 *
 */
@EnableAutoConfiguration
@EnableWebMvc
@Import({ ServiceConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class,
		ResourceServerConfiguration.class, AuthorizationServerConfiguration.class })
@ComponentScan({ "com.crossover.atrs.web" })
public class WebConfiguration {
}
