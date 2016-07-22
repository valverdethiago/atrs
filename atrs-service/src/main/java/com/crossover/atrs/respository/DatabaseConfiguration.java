package com.crossover.atrs.respository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;

@Configuration
@EnableMongoRepositories(basePackages="com.crossover.atrs")
@PropertySource(value="classpath:atrs.properties")
public class DatabaseConfiguration {


	@Bean(name="mongoTemplate")
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new Fongo("agilelink").getMongo(), "agilelink");
	}


}
